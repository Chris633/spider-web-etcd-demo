package com.spider.db.repository;

import com.spider.db.entity.TCrawlerWin310;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface TCrawlerWin310Repository extends JpaRepository<TCrawlerWin310, Long> {

    TCrawlerWin310 findByStartDateTimeAndUniqueId(Date startDateTime, Long uniqueId);

    TCrawlerWin310 findByUniqueId(Long uniqueId);

    TCrawlerWin310 findByWin310EuropeId(String europeId);

    /**
     * 查询所有联赛
     *
     * @return leagues or empty
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT matchs FROM t_crawler_win310 ORDER BY matchs")
    List<String> findMatchsDistinct();

    /**
     * 根据联赛，日期区间查找310赛事
     *
     * @param league
     * @param startDate
     * @param endDate
     * @return
     */
    List<TCrawlerWin310> findByMatchsAndUpdateTimeBetween(String league, Date startDate, Date endDate);

    /**
     * 查找最新的彩客网赛事
     * 每次排序可能会有性能问题，待优化
     *
     * @param competitionNum 赛事编号
     * @return entity or null
     */
    TCrawlerWin310 findTop1ByCompetitionNumOrderByStartDateTimeDesc(String competitionNum);
    
    @Query(nativeQuery = true, value = "SELECT WIN310_EUROPE_ID FROM t_crawler_win310 WHERE COMPETITION_NUM = :COMPETITION_NUM AND DURATION_TIME != '完场'")
    String queryEuropeId(@Param("COMPETITION_NUM")String matchCode);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM t_crawler_win310 WHERE ID IN (SELECT * FROM (SELECT min(ID) FROM t_crawler_win310 GROUP BY unique_id HAVING count(unique_id) > 1) AS b)")
    void deleteNonUniqueResult();
    
}
