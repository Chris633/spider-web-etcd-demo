package com.spider.utils;

import org.apache.log4j.Logger;

import com.spider.db.entity.CompanyOddsEntity;
import com.spider.domain.UpdateHdcOdds;
import com.spider.domain.UpdateHiloOdds;

public class CaiexOddsUtils {

    private static final Logger ERROR_LOGGER = LogHelper.getErrorLogger();

//    private static CXll a;
//
//    static CXll newInstance() {
//
//        if (a != null) {
//            return a;
//        }
//        try {
//            a = new CXll();
//        } catch (MWException e) {
//        } catch (Throwable e) {
//            LogHelper.errorLog(ERROR_LOGGER, e, "init CXll error");
//        }
//        return a;
//    }
//
//    /**
//     * 输出sup和ttg
//     *
//     * @param homeOdds
//     * @param sup
//     * @param awayOdds
//     * @param hiOdds
//     * @param ttg
//     * @param lowOdds
//     * @param durationTime
//     * @param homeScore
//     * @param awayScore
//     * @return
//     * @throws MWException
//     */
//    public static double[][] calcSUPandTTG(double homeOdds, double sup, double awayOdds, double hiOdds, double ttg, double lowOdds, double durationTime,
//                                           double homeScore, double awayScore) throws MWException {
//
//        double[] ds = new double[]{durationTime, homeScore, awayScore};
//        return newInstance().SUPandTTG(homeOdds, sup, awayOdds, hiOdds, ttg, lowOdds, ds, 0.07, 0.5);
//    }


    public static UpdateHdcOdds buildHdcUpdate(CompanyOddsEntity odds, String matchCode) {

        return new UpdateHdcOdds(matchCode, odds.getGamingCompany(), odds.getDurationTime(), odds.getScore(),
                odds.getOddsOne(), odds.getOddsThree(), odds.getOddsTwo(), odds.getHomeRedCard(),
                odds.getAwayRedCard(), odds.getOddsUpdateTime());
    }


    public static UpdateHiloOdds buildHiloUpdate(CompanyOddsEntity odds, String matchCode) {

        return new UpdateHiloOdds(matchCode, odds.getGamingCompany(), odds.getDurationTime(), odds.getScore(),
                odds.getOddsOne(), odds.getOddsThree(), odds.getOddsTwo(),
                odds.getHomeRedCard(), odds.getAwayRedCard(), odds.getOddsUpdateTime());
    }

}
