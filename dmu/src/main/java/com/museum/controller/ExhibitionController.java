package com.museum.controller;

 
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.museum.dao.DmuTicketDAO;
import com.museum.vo.DmuTicketVO;
 
 
 
 

@Controller
public class ExhibitionController {
	
	/*
	 *  exhibition.do ������ ȣ��
	 */
	@RequestMapping(value="/exhibition.do", method=RequestMethod.GET)
	public ModelAndView exhibition(String did) {
		ModelAndView mv = new ModelAndView();
		DmuTicketDAO dao = new DmuTicketDAO();
		DmuTicketVO vo = dao.select(did);
		
		
		/*
		 * int startCount = 0; int endCount = 0;
		 * 
		 * // ArrayList<ExhibitionVO> list = dao.select(startCount, endCount);
		 */		
		mv.addObject("vo",vo);
		mv.setViewName("ticket/exhibition/exhibition");
		
		return mv;
	}
 
	@RequestMapping(value="/learn.do", method=RequestMethod.GET)
	public String learn() {
		return "ticket/learn/learn";
	}
	
	@RequestMapping(value="/event.do", method=RequestMethod.GET)
	public String event() {
		return "ticket/event/event";
	}
	
	
	
	 
	
	
	 
	 
	 
}
