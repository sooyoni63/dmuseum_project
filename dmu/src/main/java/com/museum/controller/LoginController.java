package com.museum.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.museum.service.EmailServiceImpl;
import com.museum.service.LoginServiceImpl;
import com.museum.vo.DmuMemberVO;
import com.museum.vo.DmuSessionVO;

@Controller
public class LoginController {

	@Autowired
	private EmailServiceImpl emailService;
	
	@Autowired
	private LoginServiceImpl loginService;
	
	/***************************** ���̵� ã�� ***********************************/
	//emailCode.do : �̸��� ������ȣ �߼�
	@ResponseBody
	@RequestMapping(value = "/emailCode.do", method = RequestMethod.POST)
	public String emailCode(String email) {
		return emailService.emailForm(email);
	}
	
	//emailCheck.do : �̸� �� �̸��� ���� Ȯ��
	@ResponseBody
	@RequestMapping(value = "/emailCheck.do", method = RequestMethod.POST)
	public String emailCheck(String email, String name) {
		int result = loginService.emailCheckId(email, name);

		return String.valueOf(result);
	}
	
	/***************************** ��й�ȣ ã�� **********************************/
	//password_alter.do : ��й�ȣ ó��
	@RequestMapping(value = "/password_alter.do", method = RequestMethod.POST)
	public ModelAndView password_alter(DmuMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		//��й�ȣ ������Ʈ
		DmuSessionVO pw_result = loginService.login(vo);
		if(pw_result.getLoginresult() == 0){
			int result = loginService.loginUpdate(vo);
			if(result == 1) {
				mv.addObject("result", result);
				mv.setViewName("/login/login_find");
			}else {
				mv.setViewName("error_page");
			}
		}else {
			mv.addObject("vo", vo);
			mv.addObject("pw_result", pw_result.getLoginresult());
			mv.setViewName("/login/login_pw");
		}
		
		return mv;
	}
	
	//login_pw.do : ��й�ȣ �纯�� ������
	@RequestMapping(value = "/login_pw.do", method = RequestMethod.GET)
	public ModelAndView login_pw(DmuMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("mid", vo.getMid());
		mv.setViewName("/login/login_pw");
		return mv;
	}
	
	//pass_findOk.do : �̸� �� ���̵�, �̸��� ���� Ȯ��
	@RequestMapping(value = "/pass_findOk.do", method = RequestMethod.POST)
	public ModelAndView pass_findOk(DmuMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("vo", vo);
		mv.setViewName("/login/login_pw");
		
		return mv;
	}
	
	//emailCheckPass.do : �̸� �� ���̵�, �̸��� ���� Ȯ��
	@ResponseBody
	@RequestMapping(value = "/emailCheckPass.do", method = RequestMethod.POST)
	public String emailCheckPass(String email, String name, String mid) {
		int result = loginService.emailCheckPass(email, name, mid);
		
		return String.valueOf(result);
	}
	
	//login_findOk.do : ���̵� ã�� �Ϸ� ������
	@RequestMapping(value = "/login_find.do", method = RequestMethod.GET)
	public ModelAndView login_findOk(DmuMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/login/login_find");
		
		return mv;
		//return "/login/login_find";
	}
	
	//login_findOk_Check.do : ���̵� ��ȯ
	@RequestMapping(value = "/login_findOk.do", method = RequestMethod.POST)
	public ModelAndView login_findOk_Check(DmuMemberVO vo) {
		ModelAndView mv = new ModelAndView();

		String result = loginService.findId(vo);

		if(!result.equals("")) {
			mv.addObject("find_information", vo.getMname());
			mv.addObject("find_result", result);
			mv.setViewName("/login/login_find");
		}else {
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	//login_idfind.do : ���̵�/��й�ȣ ã�� ������ ȣ��
	@RequestMapping(value = "/login_idfind.do", method = RequestMethod.GET)
	public ModelAndView login_idfind(String show) {
		ModelAndView mv = new ModelAndView();
		if(show != null) {
			mv.addObject("show", show);
		}
		mv.setViewName("/login/login_idfind");
		
		return mv;
	}
	
	//login.do : �α��� ������ ȣ��
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(String login_result) {
		return "/login/login";
	}
	
	//�α��� üũ
	@ResponseBody
	@RequestMapping(value = "/login_check.do", method = RequestMethod.POST)
	public ModelAndView login_check(DmuMemberVO vo, HttpServletRequest request, 
									HttpServletResponse response, boolean remeberId) throws Exception {
		ModelAndView mv = new ModelAndView();

		DmuSessionVO member = loginService.login(vo);
		
		if(member != null) {
			//��� �� �Ϲ� ȸ�� ����
			if(!member.getStatus().equals("public")) {
				//��� Ȯ��
				if(member.getStatus().equals("accept")) {
					mv.addObject("login_result", "accept");
					mv.setViewName("/login/login");
				}
			} else {
				//�Ϲ�ȸ���� ��
				if(member.getLoginresult() == 1) {
					//���� ����
					HttpSession session = request.getSession();
					session.setAttribute("member", member);
					
					//��Ű ���� ���� Ȯ��
					Cookie cookie = new Cookie("rememberId", vo.getMid());
					if(remeberId) {
						//üũ �ڽ� true ��, cookie ����
						response.addCookie(cookie);
					}else {
						//üũ �ڽ� false ��, cookie ����
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
					
					mv.addObject("login_result", member.getLoginresult());
					mv.setViewName("index");
				}
			}
		}else {
			mv.addObject("login_result", "fail");
			mv.setViewName("/login/login");
		}

		return mv;
	}
}
