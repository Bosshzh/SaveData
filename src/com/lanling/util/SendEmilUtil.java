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
		//阿里云服务器禁用25端口
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties,new Authenticator(){
		public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("bmwdlina@163.com", "wyyalx513123"); //发件人邮件用户名、密码
			}
		});
		// 创建默认的 MimeMessage 对象
		MimeMessage message = new MimeMessage(session);
		try {
			// Set From: 头部头字段
			message.setFrom(new InternetAddress("bmwdlina@163.com"));
			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo));
			// Set Subject: 头部头字段
			message.setSubject(head);
			// 设置消息体
			message.setText(content);
	        // 发送消息
	        Transport.send(message);
	        return true;
		}catch(MessagingException e) {
			return false;
		}
	}
}