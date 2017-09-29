package com.spider.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spider.db.entity.ManualMatchesEntity;
import com.spider.db.entity.ManualMatchesHistoryEntity;
import com.spider.db.repository.ManualMatchesHistoryRepository;
import com.spider.db.repository.ManualMatchesRepository;
import com.spider.manager.model.JsonResult;
import com.spider.manager.service.ManualMatchService;

@Service
public class ManualMatchServiceImpl implements ManualMatchService{

	@Autowired
	private ManualMatchesRepository manualMatchesRepository;
	
	@Autowired
	private ManualMatchesHistoryRepository manualMatchesHistoryRepository;
	
	
	@Override
	public JsonResult addMatch(String europeId, Long uniqueId, String w500Id) {
		ManualMatchesEntity manualMatchesEntity = new ManualMatchesEntity();
		manualMatchesEntity.setEuropeId(europeId);
		manualMatchesEntity.setUniqueId(uniqueId);
		manualMatchesEntity.setW500Id(w500Id);
		try {
			manualMatchesRepository.save(manualMatchesEntity);
			return JsonResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(1, "persist error");
		}
	}

	@Override
	public JsonResult deleteMatch(String europeId, Long uniqueId, String w500Id) {
		
		try {
			ManualMatchesEntity manualMatchesEntity = manualMatchesRepository.findByEuropeIdAndUniqueIdAndW500Id(europeId, uniqueId, w500Id);
			ManualMatchesHistoryEntity manualMatchesHistoryEntity = new ManualMatchesHistoryEntity(manualMatchesEntity);
			manualMatchesHistoryRepository.save(manualMatchesHistoryEntity);
			manualMatchesRepository.delete(manualMatchesEntity);
			return JsonResult.SUCCESS;
		} catch (NullPointerException e) {
			return new JsonResult(1, "match does not exist");
		} catch (Exception e) {
			return new JsonResult(1, "delete match fail");
		}
	}

	@Override
	public JsonResult addBatchMatch(String eiduidPairs) {
		return null;
	}

	@Override
	public JsonResult deleteBatchMatch(String eiduidPairs) {
		return null;
	}

}
