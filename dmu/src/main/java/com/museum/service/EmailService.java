package com.museum.service;

public interface EmailService {
	void getCode();					//������ȣ ���� �߻�
	String emailForm(String email);	//�̸��� ����
	void mailSend(String fromMail, String toMail, String title, String content);
}
