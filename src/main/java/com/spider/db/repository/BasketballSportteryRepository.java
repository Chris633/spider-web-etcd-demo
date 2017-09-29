package com.spider.db.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spider.db.entity.BasketballSportteryEntity;

public interface BasketballSportteryRepository extends JpaRepository<BasketballSportteryEntity, Long> {
	
	public List<BasketballSportteryEntity> findByStartDateBetween(Date start, Date end);
	
	public BasketballSportteryEntity findByUniqueId(Long uniqueId);

}
