package com.spider.manager.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spider.manager.model.JsonResult;
import com.spider.manager.service.SbcService;

/**
 * 同步sbc的Controller
 *
 * @author ronnie
 */
@Controller
public class SbcSyncController {

    @Autowired
    private SbcService sbcService;

    /**
     * @param matchCode
     * @param type
     * @return
     * @see SbcService
     */
    @RequestMapping(value = "/sync.do", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult sync(@RequestParam String matchCode, @RequestParam(required = false) Integer type) {

        return sbcService.sync(matchCode, type);
    }

    /**
     * @param id
     * @return
     * @see SbcService
     */
    @RequestMapping(value = "/syncOdds", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult syncOdds(@RequestParam String id) {
    	String[] split = id.split(",");
    	for (int i = 0; i < split.length; i++) {
			sbcService.syncOdds(split[i]);
		}
        return JsonResult.SUCCESS;
    }
    
    /**
     * @param matchCode
     * @param type
     * @return
     * @see SbcService
     */
    @RequestMapping(value = "/queryNowgoalURL.do", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String findEuropeId(@RequestParam String matchCode, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	String queryNowgoalURL = sbcService.queryNowgoalURL(matchCode);
        return queryNowgoalURL;
    }
    
    /**
     * @param matchCode
     * @param type
     * @return
     * @see SbcService
     */
    @RequestMapping(value = "/syncBasketball.do", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult syncBasketballScore(@RequestParam String matchCode, @RequestParam(required = false) Integer type) {

        return sbcService.syncBasketball(matchCode, type);
    }

    /**
     * @param id
     * @return
     * @see SbcService
     */
    @RequestMapping(value = "/syncBasketballOdds", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult syncsyncBasketballOdds(@RequestParam String id) {
    	String[] split = id.split(",");
    	for (int i = 0; i < split.length; i++) {
			sbcService.syncBasketballOdds(split[i]);
		}
        return JsonResult.SUCCESS;
    }

}
