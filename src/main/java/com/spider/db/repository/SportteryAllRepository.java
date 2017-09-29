package com.spider.db.repository;


import com.spider.db.entity.SportteryAllEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SportteryAllRepository extends JpaRepository<SportteryAllEntity, Long> {

    SportteryAllEntity findByMatchCode(String matchCode);
    
    SportteryAllEntity findByUniqueId(Long uniqueId);
    
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM sporttery_all WHERE id IN (SELECT * FROM (SELECT min(id) FROM sporttery_all GROUP BY unique_id HAVING count(unique_id) > 1) AS b)")
    void deleteNonUniqueResult();
}
