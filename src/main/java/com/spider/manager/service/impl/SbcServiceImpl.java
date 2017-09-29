package com.spider.manager.service.impl;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spider.db.entity.BasketballOddsEntity;
import com.spider.db.entity.BasketballScoreEntity;
import com.spider.db.entity.CompanyOddsEntity;
import com.spider.db.entity.ManualMatchesEntity;
import com.spider.db.entity.NowgoalScoreEntity;
import com.spider.db.entity.TCrawlerWin310;
import com.spider.db.entity.W500Entity;
import com.spider.db.repository.BasketballOddsRepository;
import com.spider.db.repository.BasketballScoreRepository;
import com.spider.db.repository.CompanyOddsRepository;
import com.spider.db.repository.ManualMatchesRepository;
import com.spider.db.repository.NowgoalScoreRepository;
import com.spider.db.repository.TCrawlerWin310Repository;
import com.spider.db.repository.W500Repository;
import com.spider.domain.UpdateBasketballOdds;
import com.spider.domain.UpdateBasketballScore;
import com.spider.domain.UpdateHdcOdds;
import com.spider.domain.UpdateHiloOdds;
import com.spider.domain.UpdateScoreAndHalf;
import com.spider.exception.UpdateException;
import com.spider.manager.model.JsonResult;
import com.spider.manager.sbc.SbcUpdateManager;
import com.spider.manager.service.SbcService;
import com.spider.utils.CaiexOddsUtils;

/**
 * Created by wsy on 2015/10/21.
 * 
 * @author ronnie
 */
@Service
public class SbcServiceImpl implements SbcService {

	public static final int ODDS_TYPE_HILO = 2;

	private Logger logger = Logger.getLogger("info_logger");

	@Value("${inplay.odds.hdc.tag}")
	private String hdcTag;

	@Value("${inplay.odds.hilo.tag}")
	private String hiloTag;

	@Value("${inplay.odds.score_half.tag}")
	private String scoreAndHalfTag;

	@Value("${inplay.odds.topic}")
	private String inplayOddsTopic;

	@Value("${inplay.odds.topic.parameter}")
	private String inplayParameterTopic;
	
	@Value("${basketball.odds.topic}")
	private String basketballOddsTopic;
	
	@Value("${basketball.score.topic}")
	private String basketballScoreTopic;
	
	@Value("${basketball.odds.hdc.tag}")
	private String basketballHdcOddsTag;
	
	@Value("${basketball.odds.hilo.tag}")
	private String basketballHiloOddsTag;
	
	@Value("${basketball.score.tag}")
	private String basketballScoreTag;

	@Autowired
	private SbcUpdateManager sbcUpdateManager;

	@Autowired
	private TCrawlerWin310Repository win310Repository;

	@Autowired
	private W500Repository w500Repository;

	@Autowired
	private CompanyOddsRepository companyOddsRepository;

	@Autowired
	private ManualMatchesRepository manualMatchesRepository;
	
	@Autowired
	private NowgoalScoreRepository nowgoalScoreRepository;
	
	@Autowired
	private BasketballScoreRepository basketballScoreRepository;
	
	@Autowired
	private BasketballOddsRepository basketballOddsRepository;

	/**
	 * 同步赔率信息，根据id进行查找
	 * 
	 * @param id
	 *            not null
	 * @return
	 * @see UpdateHiloOdds
	 * @see UpdateHdcOdds
	 */
	@Override
	public JsonResult syncOdds(String id) {

		if (StringUtils.isEmpty(id)) {
			return null;
		}

		try {
			CompanyOddsEntity odds = companyOddsRepository.findOne(Long.valueOf(id));
			if (odds == null) {
				logger.error("no this odds, id is [" + id + "]");
				return new JsonResult(1, "no this odds, id is [" + id + "]");
			}
			String europeId = odds.getEuropeId() + "";
			TCrawlerWin310 win310 = win310Repository
					.findByWin310EuropeId(europeId);
			String matchCode = null;
			if (win310 == null) {
				ManualMatchesEntity manualMatchesEntity = manualMatchesRepository
						.findByEuropeId(odds.getEuropeId().toString());
				if (manualMatchesEntity == null) {
					logger.error("no this win310 and manualMatch, europeId is [" + europeId + "]");
					return new JsonResult(2, "no this win310 and manualMatch, europeId is [" + europeId + "]");
				}
				String uniqueId = manualMatchesEntity.getUniqueId().toString();
				matchCode = uniqueId.substring(uniqueId.length() - 4);
			} else {
				matchCode = win310.getCompetitionNum();
			}
			doUpdateToMQ(matchCode, odds);
			return JsonResult.SUCCESS;
		} catch (UpdateException e) {
			logger.error("mq error", e);
			return new JsonResult(2, "mq error");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new JsonResult(1, e.getMessage());
		}
	}

	@Override
	public JsonResult sync(String uniqueId, Integer type) {
		JsonResult jsonResult;
		try {
			jsonResult = useNewScoreCrawler(uniqueId, type);
		} catch (Exception e) {
			logger.error(e);
			jsonResult = useOldScoreCrawler(uniqueId, type);
		}
		return jsonResult;
	}

	private JsonResult useNewScoreCrawler(String uniqueId, Integer type) throws Exception {
//		W500Entity w500Entity;
		NowgoalScoreEntity nowgoalScoreEntity;
		try {
			nowgoalScoreEntity = nowgoalScoreRepository.findByUniqueId(Long.valueOf(uniqueId));
//			w500Entity = w500Repository.findByMatchCode(uniqueId);

			if (nowgoalScoreEntity == null) {
				logger.error("no NowgoalScoreEntity found for this uniqueId [" + uniqueId + "]");
//				return new JsonResult(1, "no w500 and manualMatch entity for this uniqueId [" + uniqueId + "]");
				throw new Exception("no NowgoalScoreEntity found for this uniqueId [" + uniqueId + "]");
			}
		} catch (Exception e) {
			logger.error("mysql error", e);
//			return new JsonResult(1, "mysql error, " + e.getMessage());
			throw e;
		}

		try {
			updateSbcScoreAndHalf(nowgoalScoreEntity);

			if (type == null) {
				updateHiloAndHdcOdds(uniqueId, Integer.valueOf(nowgoalScoreEntity.getW500Id()));
			}

			return JsonResult.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
//			return new JsonResult(1, "unexpected error");
			throw e;
		}
	}

	private JsonResult useOldScoreCrawler(String uniqueId, Integer type) {
		TCrawlerWin310 win310;
		W500Entity w500Entity;
		ManualMatchesEntity manualMatchesEntity = null;
		Integer europeId = null;
		try {
			win310 = win310Repository.findByUniqueId(Long.valueOf(uniqueId));
			manualMatchesEntity = manualMatchesRepository.findByUniqueId(Long.valueOf(uniqueId));
			if (win310 == null) {
				if (manualMatchesEntity == null) {
					logger.error("no win310 and manualMatch entity for this uniqueId [" + uniqueId + "]");
					return new JsonResult(1, "no win310 and manualMatch entity for this uniqueId [" + uniqueId + "]");
				}
				europeId = Integer.valueOf(manualMatchesEntity.getEuropeId());
			} else {
				europeId = Integer.valueOf(win310.getWin310EuropeId());
			}
			w500Entity = w500Repository.findByUniqueId(Long.valueOf(uniqueId));
			if (w500Entity == null && manualMatchesEntity == null) {
				logger.error("no w500 entity or manualMatch  for this uniqueId [" + uniqueId + "]");
				return new JsonResult(1, "no w500 and manualMatch entity for this uniqueId [" + uniqueId + "]");
			}
		} catch (Exception e) {
			logger.error("mysql error", e);
			return new JsonResult(1, "mysql error, " + e.getMessage());
		}
		try {
			if (w500Entity != null) {
				updateSbcScoreAndHalf(w500Entity);
			}/* else {
				updateSbcScoreAndHalf(manualMatchesEntity);
			}*/

			if (type == null) {
				updateHiloAndHdcOdds(uniqueId, europeId);
			}
			return JsonResult.SUCCESS;
		} catch (UpdateException e) {
			logger.error("update error", e);
			return new JsonResult(2, "update error");
		} catch (Exception e) {
			logger.error("", e);
			return new JsonResult(1, "unexpected error");
		}
	}

	private static final String NOWGOAL_URL_TEMPLATE = "http://data.nowgoal.com/3in1odds/{0}.html";

	@Override
	public String queryNowgoalURL(String matchCode) {
		String queryEuropeId = win310Repository.queryEuropeId(matchCode);
		if (queryEuropeId == null) {
			logger.error("europeId not found for " + matchCode);
			return null;
		}
		String format = MessageFormat.format(NOWGOAL_URL_TEMPLATE, queryEuropeId);
		System.out.println(format);
		return format;
	}

	private void updateHiloAndHdcOdds(String uniqueId, Integer europeId) throws UpdateException {
		String matchCode = uniqueId.substring(8);
		
		List<CompanyOddsEntity> hdcHilos = companyOddsRepository.findHdcAndHilo(europeId);
		for (CompanyOddsEntity odds : hdcHilos) {
			doUpdateToMQ(matchCode, odds);
		}
	}

	private void doUpdateToMQ(String matchCode, CompanyOddsEntity odds) throws UpdateException {

		if (ODDS_TYPE_HILO == odds.getOddsType()) {
			sbcUpdateManager.update(CaiexOddsUtils.buildHiloUpdate(odds, matchCode), hiloTag);
		} else {
			sbcUpdateManager.update(CaiexOddsUtils.buildHdcUpdate(odds, matchCode), hdcTag);
		}
	}

	private void updateSbcScoreAndHalf(W500Entity w500) throws UpdateException {
		String score = w500.getScore();
		String half = w500.getHalf();
		String halfScore = w500.getHalfScore();
		Integer homeRedCard = w500.getHomeRedCard();
		Integer awayRedCard = w500.getAwayRedCard();
		Integer timeMinute;
		try {
			timeMinute = Integer.valueOf(w500.getDurationTime());
		} catch (Exception e) {
			timeMinute = null;
		}
		UpdateScoreAndHalf scoreAndHalf = new UpdateScoreAndHalf(w500.getMatchCode().toString(), score, halfScore, half, homeRedCard, awayRedCard, timeMinute);
		sbcUpdateManager.update(scoreAndHalf, scoreAndHalfTag, inplayParameterTopic);
	}
	
	private void updateSbcScoreAndHalf(NowgoalScoreEntity nowgoalScoreEntity) throws UpdateException {
		String score = nowgoalScoreEntity.getScore();
		String half = nowgoalScoreEntity.getHalf();
		String halfScore = nowgoalScoreEntity.getHalfScore();
		Integer homeRedCard = nowgoalScoreEntity.getHomeRedCard();
		Integer awayRedCard = nowgoalScoreEntity.getAwayRedCard();
		Integer timeMinute;
		try {
			timeMinute = Integer.valueOf(nowgoalScoreEntity.getDurationTime());
		} catch (Exception e) {
			timeMinute = null;
		}
		UpdateScoreAndHalf scoreAndHalf = new UpdateScoreAndHalf(nowgoalScoreEntity.getMatchCode().toString(), score, halfScore, half, homeRedCard, awayRedCard, timeMinute);
		sbcUpdateManager.update(scoreAndHalf, scoreAndHalfTag, inplayParameterTopic);
	}

	@Override
	public JsonResult syncBasketball(String uniqueId, Integer type) {
		try {
			BasketballScoreEntity basketballScoreEntity = basketballScoreRepository.findByUniqueId(Long.valueOf(uniqueId));
			if (basketballScoreEntity == null) {
				logger.warn("No Match Score Found. UniqueId: " + uniqueId);
			} else {
				UpdateBasketballScore updateBasketballScore = new UpdateBasketballScore(basketballScoreEntity);
				sbcUpdateManager.update(updateBasketballScore, basketballScoreTag, basketballScoreTopic);
			}
			
			if (type == null) {
//				List<BasketballOddsEntity> basketballOddsEntities = basketballOddsRepository.findByEuropeId(basketballScoreEntity.getEuropeId());
				List<BasketballOddsEntity> basketballOddsEntities = basketballOddsRepository.findByUniqueId(Long.valueOf(uniqueId));
				if (basketballOddsEntities == null || basketballOddsEntities.isEmpty()) {
					logger.warn("No Match Odds Found. UniqueId: " + uniqueId);
				} else {
					for (BasketballOddsEntity basketballOddsEntity : basketballOddsEntities) {
						sendBasketballOdds(basketballOddsEntity);
					}
				}
			}
		} catch (UpdateException e) {
			e.printStackTrace();
			logger.error("uniqueId: " + uniqueId, e);
			return new JsonResult(1, "Error Occured. " + e);
		}
		return JsonResult.SUCCESS;
	}

	@Override
	public JsonResult syncBasketballOdds(String id) {
		try {
			BasketballOddsEntity basketballOddsEntity = basketballOddsRepository.findOne(Long.valueOf(id));
			sendBasketballOdds(basketballOddsEntity);
		} catch (UpdateException e) {
			e.printStackTrace();
			logger.error(id, e);
		}
		return JsonResult.SUCCESS;
	}
	
	private void sendBasketballOdds(BasketballOddsEntity basketballOddsEntity) throws UpdateException {
		UpdateBasketballOdds updateBasketballOdds = new UpdateBasketballOdds(basketballOddsEntity);
		if (basketballOddsEntity.getOddsType().equals(0)) {
			sbcUpdateManager.update(updateBasketballOdds, basketballHdcOddsTag, basketballOddsTopic);
		} else if (basketballOddsEntity.getOddsType().equals(1)) {
			sbcUpdateManager.update(updateBasketballOdds, basketballHiloOddsTag, basketballOddsTopic);
		} else {
			throw new UpdateException("Odds Type Error. Please Check basketball_odds. EuropeId: " + basketballOddsEntity.getEuropeId());
		}
	}
	
}
