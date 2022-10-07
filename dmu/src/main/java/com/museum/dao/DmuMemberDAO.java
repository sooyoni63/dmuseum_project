package com.museum.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.museum.vo.DmuMemberVO;
import com.museum.vo.DmuSessionVO;

public class DmuMemberDAO extends DBConn{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace = "mapper.member";
	
	//login_update : ��й�ȣ �纯��
	public int login_update(DmuMemberVO vo) {
		return sqlSession.update(namespace + ".login_update", vo);
	}
	
	//emailCheckPass : ��й�ȣ ã�� email ��ġ Ȯ��
	public int emailCheckPass(String email, String name, String mid) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("email", email);
		param.put("name", name);
		param.put("mid", mid);
		
		return sqlSession.selectOne(namespace + ".email_check_pass", param);
	}
	
	//selectId : id ã��
	public String selectId(DmuMemberVO vo) {
		return sqlSession.selectOne(namespace + ".idfind", vo);
	}
	
	//emailCheckId : ���̵� ã�� email ��ġ Ȯ��
	public int emailCheckId(String email, String name) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("email", email);
		param.put("name", name);
		
		return sqlSession.selectOne(namespace + ".id_find_email", param);
	}
	
	//login : �α���
	public DmuSessionVO login(DmuMemberVO vo) {
		return sqlSession.selectOne(namespace + ".login", vo);
	}
	
	//idCheck : ȸ�� ���� �� �ߺ� ���̵� üũ
	public int idCheck(String mid) {
		return sqlSession.selectOne(namespace + ".idcheck", mid);
	}
	
	//insert : ȸ�� ���� ���� �� ȸ�� ������ ����
	public int insert(DmuMemberVO vo) {
		return sqlSession.insert(namespace + ".insert", vo);
	}
}
