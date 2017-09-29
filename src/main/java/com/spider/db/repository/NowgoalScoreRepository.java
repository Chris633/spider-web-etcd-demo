package com.spider.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spider.db.entity.NowgoalScoreEntity;

public interface NowgoalScoreRepository extends JpaRepository<NowgoalScoreEntity, Long>{
	
	public NowgoalScoreEntity findByUniqueId(Long uniqueId);
}
