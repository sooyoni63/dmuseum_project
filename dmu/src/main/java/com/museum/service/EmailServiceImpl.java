package com.museum.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	int check_code;

	//������ȣ ���� �߻� (111111 ~ 999999)
	@Override
	public void getCode() {
		Random random = new Random();
		int code = random.nextInt(888888) + 111111;
		
		System.out.println(code);
		check_code = code;
	}
	
	//�̸��� ����
	@Override
	public String emailForm(String email) {
		getCode();
		//ȣ��Ʈ �̸��Ͽ� �ڽ��� ������ ���� ��! �ڽ��� ����(�߽���)���� String email(������)�� ���� ����� �̸��Ϸ� �޽����� ���۵�.
		String fromMail = "ȣ��Ʈ �̸���";
		String toMail = email;
		String title = "[D'MUSEUM] ���� ��ȣ �Դϴ�.";
		String content = "[D'MUSEUM] Ȩ�������� �̿����ּż� �����մϴ�.<br><br>"
				+ "��û�Ͻ� ������ȣ�� [" + check_code + "] �Դϴ�.<br>"
				+ "������ȣ Ȯ�ζ��� ��Ȯ�ϰ� �����Ͽ� �ּ���.";
		
		mailSend(fromMail, toMail, title, content);
		
		//�������� ���������� ��ȯ(login_idfind.do�� ��ȯ)
		return Integer.toString(check_code);
	}
	
	//�̸��� ����
	@Override
	public void mailSend(String fromMail, String toMail, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom(fromMail);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
