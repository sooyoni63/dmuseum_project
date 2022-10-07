package com.museum.service;

import com.museum.vo.DmuMemberVO;
import com.museum.vo.DmuSessionVO;

public interface LoginService {

	/**
	 * emailCheck(email,name) : �̸� �� �̸��� üũ
	 */
	public int emailCheckId(String email, String name);
	
	//loginUpdate(DmuMemberVO vo) : ��й�ȣ ������Ʈ
	public int loginUpdate(DmuMemberVO vo);
	
	//��й�ȣ ã�� �������� ���� ���� ���� Ȯ��
	public int emailCheckPass(String email, String name, String mid);

	//���̵� ��ȯ
	public String findId(DmuMemberVO vo);
	
	//�α��� üũ
	public DmuSessionVO login(DmuMemberVO vo);
}
