package com.museum.service;

import com.museum.vo.DmuMemberVO;

public interface JoinService {
	//ȸ�� ����
	public int join(DmuMemberVO vo);
	
	//���̵� �ߺ�Ȯ��
	public int idCheck(String mid);

}
