package com.spider.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spider.db.entity.BasketballLines;

public interface BasketballLinesRepository extends JpaRepository<BasketballLines, Long> {

	public BasketballLines findByUniqueId(Long uniqueid);
}
