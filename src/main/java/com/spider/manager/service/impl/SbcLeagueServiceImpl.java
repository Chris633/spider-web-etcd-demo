package com.spider.manager.service.impl;

import javax.persistence.NonUniqueResultException;

import com.spider.db.entity.SbcLeague;
import com.spider.db.entity.TCrawlerSporttery;
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
}
