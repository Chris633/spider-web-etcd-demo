package com.spider.manager.service;

import com.spider.manager.model.JsonResult;



public interface ManualMatchService {
	
	JsonResult addMatch(String europeId, Long uniqueId, String w500Id);
	
	JsonResult deleteMatch(String europeId, Long uniqueId, String w500Id);
	
	JsonResult addBatchMatch(String eiduidPairs);
	
	JsonResult deleteBatchMatch(String eiduidPairs);
	
}
