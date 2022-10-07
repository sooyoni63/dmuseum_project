package com.museum.service;

import java.util.ArrayList;

import com.museum.dao.DmuNoticeDAO;
import com.museum.vo.DmuNoticeVO;

public class NoticeServiceImpl implements NoticeService{

	
	/*
	 * �������� �� �ο� ��
	 */
	@Override
	public int getTotalCount(){
		DmuNoticeDAO dao = new DmuNoticeDAO();
		int dbCount = dao.totalCount();
		return dbCount;
	}
	
	/*
	 * �������� ����Ʈ
	 */
	@Override
	public ArrayList<DmuNoticeVO> getList(int startCount, int endCount){
		DmuNoticeDAO dao = new DmuNoticeDAO();
		ArrayList<DmuNoticeVO> list = dao.select(startCount, endCount);
		return list;
	}
	
	/*
	 * �������� �󼼺���
	 */
	@Override
	public DmuNoticeVO getContent(String nid) {
		DmuNoticeDAO dao = new DmuNoticeDAO();
		DmuNoticeVO vo = dao.select(nid);
		return vo;
	}
	
	/*
	 * �������� �۾���
	 */
	@Override
	public int getWriteResult(DmuNoticeVO vo) {
		DmuNoticeDAO dao = new DmuNoticeDAO();
		int result = dao.insert(vo);
		return result;
	}
	
	/*
	 * �������� ����
	 */
	@Override
	public int getUpdateResult(DmuNoticeVO vo) {
		DmuNoticeDAO dao = new DmuNoticeDAO();
		int result = dao.update(vo);
		return result;
	}
	
	/*
	 * �������� ���� 
	 */
	
	@Override
	public int getDeleteResult(String nid) {
		DmuNoticeDAO dao = new DmuNoticeDAO();
		int result = dao.delete(nid);
		return result;
	}
	
	
	/*
	 * ī�װ�
	 */
	
	public int getTotalCount_category(String ncategory) {
		DmuNoticeDAO dao = new DmuNoticeDAO();
		int result = dao.totalCount_category(ncategory);
		return result;
	}
}
