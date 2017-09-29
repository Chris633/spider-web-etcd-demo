package com.spider.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spider.db.entity.BasketballScoreEntity;

public interface BasketballScoreRepository extends JpaRepository<BasketballScoreEntity, Long> {

	public BasketballScoreEntity findByEuropeId(Integer europeId);
	
	public BasketballScoreEntity findByUniqueId(Long uniqueId);
}
