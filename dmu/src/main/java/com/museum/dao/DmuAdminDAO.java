package com.museum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.museum.vo.DmuMemberVO;

@Repository
public class DmuAdminDAO extends DBConn{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	/*
	 * adminpage_member_list : ���� ������ ȸ�� ����Ʈ ��� 
	 */
	public ArrayList<DmuMemberVO> memberList(int startCount, int endCount) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<DmuMemberVO> mlist = sqlSession.selectList("mapper.admin.memberList", param);
		
		return (ArrayList<DmuMemberVO>) mlist;
	}
	
	
	/*
	 * totalCount() : ȸ�� �� �ο��
	 */
	
	public int totalCount() {

		return sqlSession.selectOne("mapper.admin.totalCount");
	}

	
	/*
	 * adminpage_member_content : ���� ������ ȸ�� �󼼳���
	 */
	
	public DmuMemberVO memberContent(String mid) {
		return sqlSession.selectOne("mapper.admin.memberContent", mid);
	}

}
