package com.spider.manager.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Preconditions;
import com.spider.db.entity.*;
import com.spider.httputil.HttpRequest;
import com.spider.manager.model.MatchModel;
import com.spider.manager.model.MatchPlayerInfoModel;
import com.spider.db.repository.*;
import com.spider.manager.service.MatchService;
import com.spider.manager.service.SbcLeagueService;
import com.spider.utils.DateUtils;
import com.spider.utils.LogHelper;
import com.spider.utils.LotteryUtils;

import org.apache.http.HttpException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.NonUniqueResultException;

@Service
public class MatchServiceImpl implements MatchService {

    @Value("${endpoint.sbc.match_compare}")
    private String ENDPOINT_SBC_MATCH_COMPARE;

    private static Logger logger = Logger.getLogger(MatchServiceImpl.class);
    
    private static final Logger duplicateLogger = LogHelper.getDuplicateLogger();

    private static final String STATE_COMPLETELY_MATCH = "0";

    private static final String STATE_NOT_EXIST = "1";

    private static final String STATE_HAS_DIFFERENCE = "2";

    @Autowired
    private TCrawlerSportteryRepository sportteryRepository;

    @Autowired
    private TCrawlerWin310Repository win310Repository;

    @Autowired
    private SbcLeagueService sbcLeagueService;

    @Autowired
    private NowgoalMatchPlayersRepository playersRepository;

    @Autowired
    private NowgoalKeyEventRepository keyEventRepository;

    @Override
    public List<MatchModel> listMatch(Date startDate, Date endDate) {

        Preconditions.checkNotNull(startDate);
        Preconditions.checkNotNull(endDate);

        List<MatchModel> matchList = new ArrayList<>();
        List<TCrawlerSporttery> sportteryList = sportteryRepository.findByStartDateTimeBetween(startDate, endDate);
        if (sportteryList == null || sportteryList.size() == 0) {
            return matchList;
        }
        List<String> uniqueIds = LotteryUtils.getUniqueId(sportteryList);
        List<String> absenceMatchSet = getAbsenceUniqueId(uniqueIds);
        for (TCrawlerSporttery sporttery : sportteryList) {
        	try{
        		
        		TCrawlerWin310 win310 = win310Repository.findByStartDateTimeAndUniqueId(sporttery.getStartDateTime(), sporttery.getUniqueId());
        		MatchModel matchModel = new MatchModel();
        		matchModel.setId(sporttery.getId());
        		matchModel.setMatchDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", sporttery.getStartDateTime()));
        		matchModel.setMatchCode(sporttery.getCompetitionNum());
        		matchModel.setMatchLeague(sbcLeagueService.getLeagueName(sporttery));
        		matchModel.setHomeTeam(sporttery.getHomeTeam());
        		matchModel.setAwayTeam(sporttery.getVisitionTeam());
        		setModelState(sporttery, win310, absenceMatchSet, matchModel);
        		matchList.add(matchModel);
        	} catch (NonUniqueResultException | IncorrectResultSizeDataAccessException e) {
        		win310Repository.deleteNonUniqueResult();
        		duplicateLogger.error("duplicate win310:" + sporttery.getCompetitionNum(), e);
        	}
        }
        return matchList;
    }

    @Override
    public List<MatchPlayerInfoModel> listMatchByLeague(Date startDate, Date endDate, String league) {

        List<MatchPlayerInfoModel> list = new ArrayList<>();

        List<TCrawlerWin310> win310s = win310Repository.findByMatchsAndUpdateTimeBetween(league, startDate, endDate);
        for (TCrawlerWin310 win310 : win310s) {
            MatchPlayerInfoModel matchPlayerInfoModel = new MatchPlayerInfoModel();
            matchPlayerInfoModel.setDate(win310.getBDate());
            matchPlayerInfoModel.setScore(win310.getScore());
            matchPlayerInfoModel.setHalfScore(win310.getHalfScore());
            matchPlayerInfoModel.setHomeTeam(win310.getHomeTeam());
            matchPlayerInfoModel.setAwayTeam(win310.getVisitionTeam());
            Long europeId = Long.valueOf(win310.getWin310EuropeId());
            List<NowgoalMatchPlayersEntity> playersEntities = playersRepository.findByMatchId(europeId);
            List<NowgoalKeyEvent> keyEvents = keyEventRepository.findByMatchId(europeId);
            List<String> homeFirstPlayers = new ArrayList<>();
            List<String> homeSecondPlayers = new ArrayList<>();
            List<String> awayFirstPlayers = new ArrayList<>();
            List<String> awaySecondPlayers = new ArrayList<>();
            for (NowgoalMatchPlayersEntity playersEntity : playersEntities) {
                playersEntity.build(keyEvents);
                if (playersEntity.getTeam() == 1) {
                    if (playersEntity.getIsFirst()) {
                        homeFirstPlayers.add(playersEntity.getPlayer());
                    } else {
                        homeSecondPlayers.add(playersEntity.getPlayer());
                    }
                } else {
                    if (playersEntity.getIsFirst()) {
                        awayFirstPlayers.add(playersEntity.getPlayer());
                    } else {
                        awaySecondPlayers.add(playersEntity.getPlayer());
                    }
                }
            }
            matchPlayerInfoModel.setHomeFirstPlayers(homeFirstPlayers);
            matchPlayerInfoModel.setAwayFirstPlayers(awayFirstPlayers);
            matchPlayerInfoModel.setHomeSecondPlayers(homeSecondPlayers);
            matchPlayerInfoModel.setAwaySecondPlayers(awaySecondPlayers);
            list.add(matchPlayerInfoModel);
        }
        return list;
    }

    @Override
    public List<String> getAbsenceUniqueId(List<String> matchCodes) {

    	JSONArray jsonArray = new JSONArray();
    	jsonArray.addAll(matchCodes);
        List<String> absenceMatchSet = new ArrayList<>();
        try {
            HttpRequest httpRequest = HttpRequest.createRequest(ENDPOINT_SBC_MATCH_COMPARE);
            httpRequest.addParameter("matchCodes", jsonArray.toJSONString());
            String res = httpRequest.POST("");
            if (res != null) {
                List<String> absenceMatchList = Arrays.asList(res.replaceAll("\\[", "").replaceAll("\\]", "").split(","));
                for (String matchCode : absenceMatchList) {
                    absenceMatchSet.add(matchCode.trim());
                }
            }
            return absenceMatchSet;
        } catch (HttpException e) {
            logger.error("get absence match codes error", e);
        }
        return absenceMatchSet;
    }

    private void setModelState(TCrawlerSporttery sporttery, TCrawlerWin310 win310, List<String> absenceMatchSet, MatchModel matchModel) {
    	String uniqueId = sporttery.getUniqueId().toString();
        if (absenceMatchSet.contains(uniqueId)) {
            matchModel.setAbsenceState(ABSENCE_STATE_YES);
        }
        if (win310 == null) {
            matchModel.setState(STATE_NOT_EXIST);
        } else {
            if (compareTwo(sporttery, win310)) { // 彩客和官网是否有出入
                matchModel.setState(STATE_COMPLETELY_MATCH);
            } else {
                matchModel.setState(STATE_HAS_DIFFERENCE);
            }
        }
    }

    private boolean compareTwo(TCrawlerSporttery sporttery, TCrawlerWin310 win310) {// 判断竞猜官网和彩客是否匹配

        return true;// FIXME 暂时不处理
    }

}
