package com.drp.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;


/**
 * 这是一个发送邮件的工具类
 * @author curry
 *
 */
public class SendEmailUtil {
	
//	public static void main(String[] args) throws AddressException, UnsupportedEncodingException, MessagingException {
//		sendMail("875739097@qq.com", "");
//	}

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException, UnsupportedEncodingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.host", "email.cdut.edu.cn");
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("drp@cdut.cn", "mf123321");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);
		
		// 设置发送者
		message.setFrom(new InternetAddress("drp@cdut.cn", "drpAdmin", "UTF-8")); 

		// 设置发送方式与接收者
		message.setRecipient(RecipientType.TO, new InternetAddress(email, "激活者", "UTF-8")); 

		message.setSubject("数字版权保护");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 设置发件时间
        message.setSentDate(new Date());
        
		// 3.创建 Transport用于将邮件发送

		Transport.send(message);
	}
}
