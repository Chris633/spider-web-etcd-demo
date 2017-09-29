package com.spider.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spider.db.entity.BasketballOddsEntity;

public interface BasketballOddsRepository extends JpaRepository<BasketballOddsEntity, Long> {

	public BasketballOddsEntity findByEuropeIdAndCompany(Integer europeId, Integer company);
	
	public List<BasketballOddsEntity> findByEuropeId(Integer europeId);
	
	public List<BasketballOddsEntity> findByUniqueId(Long uniqueId);
}
