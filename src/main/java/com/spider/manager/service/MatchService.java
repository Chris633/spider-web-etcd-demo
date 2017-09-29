package com.spider.manager.service;

import java.util.Date;
import java.util.List;

import com.spider.manager.model.MatchModel;
import com.spider.manager.model.MatchPlayerInfoModel;

public interface MatchService {

    String ABSENCE_STATE_YES = "0";
    
    String ABSENCE_STATE_NO = "1";

    List<String> getAbsenceUniqueId(List<String> sportteries);

    /**
     * 根据时间查询比赛
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<MatchModel> listMatch(Date startDate, Date endDate);
    
    /**
     * 根据时间查询篮球比赛
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<MatchModel> listBasketballMatch(Date startDate, Date endDate);

    /**
     * 根据时间和联赛查询比赛
     *
     * @param startDate
     * @param endDate
     * @param league
     * @return
     */
    List<MatchPlayerInfoModel> listMatchByLeague(Date startDate, Date endDate, String league);

}
