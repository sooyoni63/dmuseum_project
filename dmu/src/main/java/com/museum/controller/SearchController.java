package com.museum.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.museum.dao.DmuSearchDAO;
import com.museum.service.SearchServiceImpl;
import com.museum.vo.DmuSearchVO;
import com.museum.vo.DmuTicketVO;

@Controller
public class SearchController {
	
	@Autowired
	private SearchServiceImpl searchService;
	
	//search.do : �˻� ������
	@RequestMapping(value = "/search.do", method = RequestMethod.GET)
	public String search() {
		return "/search/search";
	}
	
	//searchText.do : �˻� ó��
	@ResponseBody
	@RequestMapping(value = "/searchText.do", method = RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	public String searchText(String keyword, String dpage) {
		DmuSearchDAO dao = new DmuSearchDAO();
		ArrayList<DmuTicketVO> list = searchService.getSearchList(keyword, dpage);
		
		JsonObject jobject = new JsonObject();
		JsonArray jarray = new JsonArray();
		Gson gson = new Gson();
		
		for(DmuTicketVO vo : list) {
			JsonObject jo = new JsonObject();
			jo.addProperty("did", vo.getDid());
			jo.addProperty("dplace", vo.getDplace());
			jo.addProperty("dtitle", vo.getDtitle());
			jo.addProperty("dstart", vo.getDstart());
			jo.addProperty("dend", vo.getDend());
			jo.addProperty("dfile", vo.getDfile());
			jo.addProperty("dsfile", vo.getDsfile());
			jo.addProperty("dcode", vo.getDcode());
			jo.addProperty("dtime", vo.getDtime());
			jo.addProperty("dprice", vo.getDprice());
			jo.addProperty("dtarget", vo.getDtarget());
			jo.addProperty("dnum", vo.getDnum());
			
			jarray.add(jo);
		}
		jobject.add("list", jarray);
		
		return gson.toJson(jobject);
	}
	
	//searchTotal.do : �˻� ��� �ο� �� ��ȯ
	@ResponseBody
	@RequestMapping(value = "/searchTotal.do", method = RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	public String searchTotal(String keyword) {
		DmuSearchVO vo = searchService.getTotalCount(keyword);
		
		int total = vo.getExhibition() + vo.getLearn() + vo.getEvent();
		JsonObject jobject = new JsonObject();
		Gson gson = new Gson();
		jobject.addProperty("exhibition", vo.getExhibition());
		jobject.addProperty("learn", vo.getLearn());
		jobject.addProperty("event", vo.getEvent());
		jobject.addProperty("total", total);
		
		return gson.toJson(jobject);
	}
}
