package com.spider.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spider.db.entity.ManualMatchesEntity;


public interface ManualMatchesRepository extends JpaRepository<ManualMatchesEntity, Long> {

	ManualMatchesEntity findByEuropeIdAndUniqueIdAndW500Id(String europeId, Long uniqueId, String w500Id);

	ManualMatchesEntity findByUniqueId(Long uniqueId);
	
	ManualMatchesEntity findByEuropeId(String europeId);
}
