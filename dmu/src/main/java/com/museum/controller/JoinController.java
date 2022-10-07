package com.museum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.museum.dao.DmuMemberDAO;
import com.museum.service.JoinServiceImpl;
import com.museum.vo.DmuMemberVO;

@Controller
public class JoinController {
	
	@Autowired
	private JoinServiceImpl joinService;
	/*
	 * join_ok.do : ȸ�� ���� ���� ������
	 */
	@RequestMapping(value = "/join_ok.do", method = RequestMethod.GET)
	public String join_ok() {
		return "/join/join_ok";
	}
	
	/*
	 * joinController.do : ȸ�� ���� ����
	 */
	@RequestMapping(value = "/joinController.do", method = RequestMethod.POST)
	public ModelAndView joinController(DmuMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		int result = joinService.join(vo);
		if(result == 1) {
			mv.setViewName("/join/join_ok");
		}else {
			mv.setViewName("error_page");
		}
		return mv;
	}
	
	//idCheck.do : ���̵� �ߺ� Ȯ��
	@ResponseBody
	@RequestMapping(value = "/join_idCheck.do", method = RequestMethod.POST)
	public String idCheck(String mid) {
		int result = joinService.idCheck(mid);
		
		return String.valueOf(result);
	}
	
	//join_status.do : ȸ������ ����(admin/public)
	@RequestMapping(value = "/join_status.do", method = RequestMethod.GET)
	public String join_status() {
		return "/join/join_status";
	}

	//join_status_ok.do : ȸ�� ���� ���� üũ
	@RequestMapping(value = "/join_status_ok.do", method = RequestMethod.POST)
	public ModelAndView join_status_ok(String status) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("status", status);
		mv.setViewName("/join/join_terms");
		
		return mv;
	}
	
	/*
	 * join_terms.do : ȸ������ ������
	 */
	@RequestMapping(value = "/join_terms.do", method = RequestMethod.GET)
	public String join_terms() {
		return "/join/join_terms";
	}
	
	/*
	 * join_terms_ok.do : ��� ���� üũ
	 */
	@RequestMapping(value = "/join_terms_ok.do", method = RequestMethod.POST)
	public ModelAndView join_terms_ok(DmuMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("vo", vo);
		mv.setViewName("/join/join");
		
		return mv;
	}
	
	/*
	 * join.do : ȸ������ ������
	 */
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String join() {
		return "/join/join";
	}
	
}
