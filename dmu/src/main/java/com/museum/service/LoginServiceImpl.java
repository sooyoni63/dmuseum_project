package com.museum.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.museum.dao.DmuMemberDAO;
import com.museum.vo.DmuMemberVO;
import com.museum.vo.DmuSessionVO;

public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private DmuMemberDAO memberDao;
	
	@Override
	public int emailCheckId(String email, String name) {
		return memberDao.emailCheckId(email, name);
	}
	
	//loginUpdate(DmuMemberVO vo) : ��й�ȣ ������Ʈ
	@Override
	public int loginUpdate(DmuMemberVO vo) {
		return memberDao.login_update(vo);
	}
	
	//��й�ȣ ã�� �������� ���� ���� ���� Ȯ��
	@Override
	public int emailCheckPass(String email, String name, String mid) {
		return memberDao.emailCheckPass(email, name, mid);
	};

	//���̵� ��ȯ
	@Override
	public String findId(DmuMemberVO vo) {
		return memberDao.selectId(vo);
	}
	
	//�α��� üũ
	@Override
	public DmuSessionVO login(DmuMemberVO vo) {
		return memberDao.login(vo);
	}
}
