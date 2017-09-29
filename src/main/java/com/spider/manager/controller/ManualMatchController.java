package com.spider.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spider.manager.model.JsonResult;
import com.spider.manager.service.ManualMatchService;


@Controller
public class ManualMatchController {

	@Autowired
	private ManualMatchService manualMatchService;
	
	@RequestMapping(value = "/manualMatch/add", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult addMatch(String europeId, Long uniqueId, String w500Id) {
		return manualMatchService.addMatch(europeId, uniqueId, w500Id);
    }
	
	@RequestMapping(value = "/manualMatch/delete", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult deleteMatch(String europeId, Long uniqueId, String w500Id) {
		return manualMatchService.deleteMatch(europeId, uniqueId, w500Id);
    }
}
