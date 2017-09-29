package com.spider.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spider.db.entity.BasketballOddsAllEntity;

public interface BasketballOddsAllRepository extends JpaRepository<BasketballOddsAllEntity, Long> {
	
	public BasketballOddsAllEntity findByUniqueId(Long uniqueId); 
}
