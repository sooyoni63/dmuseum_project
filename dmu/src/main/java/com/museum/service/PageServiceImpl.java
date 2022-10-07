package com.museum.service;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class PageServiceImpl {
	
	@Autowired
	private TicketServiceImpl ticketrService;
	
	/**
	 * ����¡ ó�� 
	 */
	 public Map<String,Integer> getPageResult(String rpage,String serviceName, Object service) {
		 Map<String,Integer> param =new HashMap<String,Integer>();
		//startCount, endCount
			//����¡ ó�� - startCount, endCount ���ϱ�
			int startCount = 0;
			int endCount = 0;
			int pageSize = 5;	//���������� �Խù� ��
			int reqPage = 1;	//��û������	
			int pageCount = 1;	//��ü ������ ��
			int dbCount = 0;	//DB���� ������ ��ü ���
			
			if(serviceName.equals("ticket")) {
				ticketrService =(TicketServiceImpl)service;
				 dbCount = ticketrService.getTotalCount();
			}
			//�� ������ �� ���
			if(dbCount % pageSize == 0){
				pageCount = dbCount/pageSize;
			}else{
				pageCount = dbCount/pageSize+1;
			}
			
			//��û ������ ���
			if(rpage != null){
				reqPage = Integer.parseInt(rpage);
				startCount = (reqPage-1) * pageSize+1;
				endCount = reqPage *pageSize;
			}else{
				startCount = 1;
				endCount = pageSize;
			}
			
			//����Ÿ����  map �� ������ �߰�
			param.put("startCount", startCount);
			param.put("endCount", endCount);
			param.put("dbCount", dbCount);
			param.put("pageSize", pageSize);
			param.put("PageCount", pageCount);
			param.put("rpage", reqPage);
			
			return param;
			
	 }
}
