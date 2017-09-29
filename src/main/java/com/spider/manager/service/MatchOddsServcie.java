package com.spider.manager.service;

import java.util.Date;
import java.util.List;

import com.spider.db.entity.BasketballOddsAllEntity;
import com.spider.db.entity.OddsModel;
import com.spider.manager.model.ExcelOddsModel;

public interface MatchOddsServcie {

    /**
     * 根据日期区间查找赔率信息
     *
     * @param startDate not null
     * @param endDate   not null
     * @return
     * @see OddsModel
     */
    List<OddsModel> listOdds(Date startDate, Date endDate);

    /**
     * 根据赛事编号刷新赔率信息
     *
     * @param matchCode
     * @return
     */
    OddsModel refreshOdds(String matchCode);

    /**
     * 根据联赛和日期区间获取赔率信息，用于导出excel
     *
     * @param start
     * @param end
     * @param league
     * @return
     */
    List<ExcelOddsModel> getExcelOddsModels(Date start, Date end, String league);
    
    List<BasketballOddsAllEntity> listBasketballOdds(Date startDate, Date endDate);
}
