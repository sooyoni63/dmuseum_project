package com.museum.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.museum.dao.DmuMemberDAO;
import com.museum.vo.DmuMemberVO;

public class JoinServiceImpl implements JoinService {
	
	@Autowired
	private DmuMemberDAO memberDao;
	
	//ȸ�� ����
	@Override
	public int join(DmuMemberVO vo) {
		return memberDao.insert(vo);
	}
	
	//���̵� �ߺ�Ȯ��
	@Override
	public int idCheck(String mid) {
		return memberDao.idCheck(mid);
	}

}
