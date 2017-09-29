package com.spider.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spider.db.entity.ManualMatchesHistoryEntity;

public interface ManualMatchesHistoryRepository extends JpaRepository<ManualMatchesHistoryEntity, Integer> {

//	TestEntity findByEuropeIdAndUniqueId(Integer europeId, Integer uniqueId);
//
//    List<TestEntity> findByEuropeId(Integer europeId);
}
