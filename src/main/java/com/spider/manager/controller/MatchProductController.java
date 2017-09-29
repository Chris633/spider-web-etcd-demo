package com.spider.manager.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.spider.manager.model.BasketballProductModel;
import com.spider.manager.model.DownloadFileResult;
import com.spider.manager.model.ProductModel;
import com.spider.manager.service.MatchProductService;
import com.spider.utils.DateUtils;
import com.spider.utils.ExcelUtils;

/**
 * 此Controller对应奖池信息页面
 * 
 * @author ronnie
 */
@Controller
public class MatchProductController {

	private static final String SHEET_NAME = "First Sheet";

	@Value("${download.xls.path}")
	private String downloadXlsPath;

	@Autowired
	private MatchProductService matchProductService;

	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	@RequestMapping(value = "/listProduct", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<ProductModel> listProduct(@RequestParam Date startDate, @RequestParam Date endDate) {
		return matchProductService.listMatchProduct(startDate, endDate);
	}

	@RequestMapping(value = "/listBasketballProduct", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<BasketballProductModel> listBasketballProduct(@RequestParam Date startDate, @RequestParam Date endDate) {
		return matchProductService.listBasketballMatchProduct(startDate, endDate);
	}

	@RequestMapping(value = "/listProductPage")
	public String listProductPage() {

		return "listProduct";
	}

	@RequestMapping(value = "/listBasketballProductPage")
	public String listBasketballProductPage() {

		return "listBasketballProduct";
	}

	@RequestMapping(value = "/matchProductExcel", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public DownloadFileResult excel(@RequestParam String productModels) {

		List<ProductModel> productList = JSON.parseArray(productModels, ProductModel.class);
		String fileName = "product" + DateUtils.getNowStr("yyyyMMdd_HHmmss") + ".xls";
		WritableWorkbook workbook = null;
		try {
			workbook = Workbook.createWorkbook(new File(downloadXlsPath + fileName));
			WritableSheet sheet = workbook.createSheet(SHEET_NAME, 0);

			for (int i = 0; i < productList.size(); i++) {
				sheet.addCell(new Label(0, i, productList.get(i).getMatchDate()));
				sheet.addCell(new Label(1, i, productList.get(i).getMatchCode()));
				sheet.addCell(new Label(2, i, productList.get(i).getMatchLeague()));
				sheet.addCell(new Label(3, i, productList.get(i).getHomeTeam()));
				sheet.addCell(new Label(4, i, productList.get(i).getAwayTeam()));
				sheet.addCell(new Label(5, i, productList.get(i).getHandicapLine()));
				sheet.addCell(new Label(6, i, productList.get(i).getHAD()));
				sheet.addCell(new Label(7, i, productList.get(i).getHHAD()));
				sheet.addCell(new Label(8, i, productList.get(i).getHAFU()));
				sheet.addCell(new Label(9, i, productList.get(i).getCRS()));
				sheet.addCell(new Label(10, i, productList.get(i).getTTG()));
			}

			workbook.write();
		} catch (Exception e) {

		} finally {
			ExcelUtils.close(workbook);
		}
		return new DownloadFileResult(fileName);
	}

	@RequestMapping(value = "/matchBasketballProductExcel" , produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public DownloadFileResult basketballExcel(@RequestParam String productModels) {

		String fileName = "product" + DateUtils.getNowStr("yyyyMMdd_HHmmss") + ".xls";
		WritableWorkbook workbook = null;

		BasketballProductModel basketballProductModel = new BasketballProductModel();
		basketballProductModel.setMatchDate("match_date");
		basketballProductModel.setMatchCode("match_code");
		basketballProductModel.setMatchLeague("match_league");
		basketballProductModel.setHomeTeam("home_team");
		basketballProductModel.setAwayTeam("away_team");
		basketballProductModel.setHdcLines("hdc_lines");
		basketballProductModel.setHdc("HDC");
		basketballProductModel.setHiloLines("hilo_lines");
		basketballProductModel.setHilo("HILO");
		basketballProductModel.setMnl("MNL");
		basketballProductModel.setWnm("WNM");

		try {
			workbook = Workbook.createWorkbook(new File(downloadXlsPath + fileName));
			WritableSheet sheet = workbook.createSheet(SHEET_NAME, 0);

			sheet.addCell(new Label(0, 0, basketballProductModel.getMatchDate()));
			sheet.addCell(new Label(1, 0, basketballProductModel.getMatchCode()));
			sheet.addCell(new Label(2, 0, basketballProductModel.getMatchLeague()));
			sheet.addCell(new Label(3, 0, basketballProductModel.getHomeTeam()));
			sheet.addCell(new Label(4, 0, basketballProductModel.getAwayTeam()));
			sheet.addCell(new Label(5, 0, basketballProductModel.getHdcLines()));
			sheet.addCell(new Label(6, 0, basketballProductModel.getHdc()));
			sheet.addCell(new Label(7, 0, basketballProductModel.getHiloLines()));
			sheet.addCell(new Label(8, 0, basketballProductModel.getHilo()));
			sheet.addCell(new Label(9, 0, basketballProductModel.getMnl()));
			sheet.addCell(new Label(10, 0, basketballProductModel.getWnm()));
			List<BasketballProductModel> basketballProductModels = JSON.parseArray(productModels, BasketballProductModel.class);
			
			for (int i = 0, j = 1; i < basketballProductModels.size(); i++, j++) {
				basketballProductModel = basketballProductModels.get(i);
				
				sheet.addCell(new Label(0, j , basketballProductModel.getMatchDate()));
				sheet.addCell(new Label(1, j, basketballProductModel.getMatchCode()));
				sheet.addCell(new Label(2, j, basketballProductModel.getMatchLeague()));
				sheet.addCell(new Label(3, j, basketballProductModel.getHomeTeam()));
				sheet.addCell(new Label(4, j, basketballProductModel.getAwayTeam()));
				sheet.addCell(new Label(5, j, basketballProductModel.getHdcLines()));
				sheet.addCell(new Label(6, j, basketballProductModel.getHdc()));
				sheet.addCell(new Label(7, j, basketballProductModel.getHiloLines()));
				sheet.addCell(new Label(8, j, basketballProductModel.getHilo()));
				sheet.addCell(new Label(9, j, basketballProductModel.getMnl()));
				sheet.addCell(new Label(10, j, basketballProductModel.getWnm()));
			}
			
			workbook.write();
		} catch (Exception e) {

		} finally {
			ExcelUtils.close(workbook);
		}
		
		return new DownloadFileResult(fileName);
	}

}
