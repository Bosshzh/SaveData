package com.lanling.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmilUtil {
	
	public static boolean sendEmail(String emailTo,String head,String content) {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.163.com");
		properties.put("mail.smtp.auth", "true");
		//�����Ʒ���������25�˿�
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		// ��ȡĬ��session����
		Session session = Session.getDefaultInstance(properties,new Authenticator(){
		public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("bmwdlina@163.com", "wyyalx513123"); //�������ʼ��û���������
			}
		});
		// ����Ĭ�ϵ� MimeMessage ����
		MimeMessage message = new MimeMessage(session);
		try {
			// Set From: ͷ��ͷ�ֶ�
			message.setFrom(new InternetAddress("bmwdlina@163.com"));
			// Set To: ͷ��ͷ�ֶ�
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo));
			// Set Subject: ͷ��ͷ�ֶ�
			message.setSubject(head);
			// ������Ϣ��
			message.setText(content);
	        // ������Ϣ
	        Transport.send(message);
	        return true;
		}catch(MessagingException e) {
			return false;
		}
	}
}