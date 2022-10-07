package com.museum.service;

import java.util.ArrayList;

import com.museum.vo.DmuTicketVO;

public interface TicketService {
	int getWriteResult(DmuTicketVO vo); //�Խ��� �۾���
	int getUpdate(DmuTicketVO vo); //�Խ��� ������Ʈ
	int getTotalCount(); //��ü ȸ���� 
	ArrayList<DmuTicketVO> getList(int startCount,int endCount); //exhibition ��ü ����Ʈ
	DmuTicketVO getContent(String did); //
	int getDelete(String did);		//�������� ���� ó��
}
