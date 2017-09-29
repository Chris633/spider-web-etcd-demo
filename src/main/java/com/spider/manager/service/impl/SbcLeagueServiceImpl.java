package com.spider.manager.service.impl;

import java.util.List;

import javax.persistence.NonUniqueResultException;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.spider.db.entity.BasketballSbcLeague;
import com.spider.db.entity.SbcLeague;
import com.spider.db.entity.TCrawlerSporttery;
import com.spider.db.repository.BasketballSbcLeagueRepository;
import com.spider.db.repository.SbcLeagueRepository;
import com.spider.manager.service.SbcLeagueService;
import com.spider.utils.LogHelper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SbcLeagueServiceImpl implements SbcLeagueService {

    private static Logger logger = Logger.getLogger(SbcLeagueServiceImpl.class);
    
    private static final Logger duplicateLogger = LogHelper.getDuplicateLogger();

    @Autowired
    private SbcLeagueRepository sbcLeagueRepository;
    
    @Autowired
    private BasketballSbcLeagueRepository basketballSbcLeagueRepository;

    @Override
    public String getLeagueName(TCrawlerSporttery sporttery) {

        SbcLeague league;
        String leagueName = sporttery.getMatchs();
        try {
            league = sbcLeagueRepository.findBySportteryName(sporttery.getMatchs());
            if (league != null) {
                leagueName = league.getLeagueNameAbbr();
            }
        } catch (NonUniqueResultException | IncorrectResultSizeDataAccessException e) {
        	duplicateLogger.error("duplicate sbcleague: " + sporttery.getMatchs());
        } catch (Exception e) {
            logger.error("find sbc league error", e);
        }
        return leagueName;
    }

	@Override
	public List<BasketballSbcLeague> findAllBasketballLeagues() {
		return basketballSbcLeagueRepository.findAll();
	}

	@Override
	public void modifyBasketballLeague(String basketballLeague) {
		basketballSbcLeagueRepository.save(JSON.parseObject(basketballLeague, BasketballSbcLeague.class));
	}
}
