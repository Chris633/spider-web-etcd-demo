package com.spider.db.repository;

import com.spider.db.entity.OddsModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OddsModelRepository extends JpaRepository<OddsModel, Long> {

    OddsModel findByEuropeId(Integer europeId);
    
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM odds_model WHERE id IN (SELECT * FROM (SELECT min(id) FROM odds_model GROUP BY europe_id HAVING count(europe_id) > 1) AS b)")
    void deleteNonUniqueResult();
}
