package com.spider.manager.service.impl;

import com.google.common.base.Preconditions;
import com.spider.db.entity.SportteryAllEntity;
import com.spider.db.entity.TCrawlerSporttery;
import com.spider.db.entity.TCrawlerWin310;
import com.spider.manager.model.ProductModel;
import com.spider.db.repository.SportteryAllRepository;
import com.spider.db.repository.TCrawlerSportteryRepository;
import com.spider.db.repository.TCrawlerWin310Repository;
import com.spider.manager.service.MatchProductService;
import com.spider.manager.service.MatchService;
import com.spider.manager.service.SbcLeagueService;
import com.spider.utils.DateUtils;
import com.spider.utils.LogHelper;
import com.spider.utils.LotteryUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NonUniqueResultException;

@Service
public class MatchProductServiceImpl implements MatchProductService {

    private static final String STATUS_SELLING = "SELLING";
    
    private static final Logger duplicateLogger = LogHelper.getDuplicateLogger();

    @Autowired
    private TCrawlerSportteryRepository sportteryRepository;

    @Autowired
    private TCrawlerWin310Repository win310Repository;

    @Autowired
    private SportteryAllRepository sportteryAllRepository;

    @Autowired
    private SbcLeagueService sbcLeagueService;

    @Autowired
    private MatchService matchService;

    @Override
    public List<ProductModel> listMatchProduct(Date startDate, Date endDate) {

        Preconditions.checkNotNull(startDate);
        Preconditions.checkNotNull(endDate);

        List<ProductModel> productlist = new ArrayList<>();
        List<TCrawlerSporttery> sportteryList = sportteryRepository.findByStartDateTimeBetween(startDate, endDate);
        if (sportteryList == null) {
            return productlist;
        }
        List<String> uniqueIds = LotteryUtils.getUniqueId(sportteryList);
        List<String> absenceMatchSet = matchService.getAbsenceUniqueId(uniqueIds);
        for (TCrawlerSporttery sporttery : sportteryList) {
        	try {
	            TCrawlerWin310 win310 = win310Repository.findByStartDateTimeAndUniqueId(sporttery.getStartDateTime(),
	                    sporttery.getUniqueId());
	
	            ProductModel productModel = new ProductModel();
	            productModel.setID(sporttery.getId());
	            productModel.setMatchDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", sporttery.getStartDateTime()));
	            productModel.setMatchCode(sporttery.getCompetitionNum());
	            productModel.setMatchLeague(sbcLeagueService.getLeagueName(sporttery));
	            productModel.setHomeTeam(sporttery.getHomeTeam());
	            productModel.setAwayTeam(sporttery.getVisitionTeam());
	            Integer winCountTwo = sporttery.getWinCountTwo();
	            productModel.setHandicapLine(winCountTwo == null ? null : winCountTwo.toString());
	            if (STATUS_SELLING.equals(sporttery.getStatusOne())) {
	                productModel.setHAD("Y");
	            } else {
	                productModel.setHAD("N");
	            }
	            if (STATUS_SELLING.equals(sporttery.getStatusTwo())) {
	                productModel.setHHAD("Y");
	            } else {
	                productModel.setHHAD("N");
	            }
	            //todo 暂时都写为Y
//	            SportteryAllEntity sportteryAllEntity = sportteryAllRepository.findByMatchCode(sporttery.getCompetitionNum());
	            SportteryAllEntity sportteryAllEntity = sportteryAllRepository.findByUniqueId(sporttery.getUniqueId());
	            productModel.setHAFU(sportteryAllEntity != null && sportteryAllEntity.getHafuAa() != null ? "Y" : "N");
	            productModel.setCRS(sportteryAllEntity != null && sportteryAllEntity.getScore00() != null ? "Y" : "N");
	            productModel.setTTG(sportteryAllEntity != null && sportteryAllEntity.getTtg0() != null ? "Y" : "N");
	
	            setModelState(sporttery, win310, absenceMatchSet, productModel);
	            productlist.add(productModel);
        	} catch (NonUniqueResultException | IncorrectResultSizeDataAccessException e) {
        		win310Repository.deleteNonUniqueResult();
        		sportteryAllRepository.deleteNonUniqueResult();
        		duplicateLogger.error("duplicate win310: " + sporttery.getCompetitionNum(), e);
			}
        }
        return productlist;
    }

    private void setModelState(TCrawlerSporttery sporttery, TCrawlerWin310 win310, List<String> absenceMatchSet, ProductModel productModel) {
    	String uniqueId = sporttery.getUniqueId().toString();
        if (absenceMatchSet.contains(uniqueId)) {
            productModel.setAbsenceState(MatchService.ABSENCE_STATE_YES);
        }
        if (win310 == null) {// 不存在
            productModel.setState("1");
        } else {
            if (compareTwo(sporttery, win310)) {// 完全匹配
                productModel.setState("0");
            } else {// 有不同
                productModel.setState("2");
            }
        }
    }

    private boolean compareTwo(TCrawlerSporttery sporttery, TCrawlerWin310 win310) {// 判断竞猜官网和彩客是否匹配

        return true;// TODO: 2016/5/16 暂无实现
    }
}
