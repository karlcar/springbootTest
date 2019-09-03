package springBoot.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import springBoot.email.EmailConfig;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private EmailConfig emailconfig;
	
	@Autowired
	private JavaMailSender mailsender;
	
	@Override
	public void sendSimpleEmail(String sendTo, String title, String content) {
		//发送简单邮件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailconfig.getEmailFrom());
		message.setTo(sendTo);
		message.setSubject(title);
		message.setText(content);
		
		mailsender.send(message);
		
		
	}

	@Override
	public void sendAttachementEmail(String sendTo, String title, String content, File file) {
		MimeMessage msg = mailsender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setFrom(emailconfig.getEmailFrom());
			helper.setTo(sendTo);
			helper.setSubject(title);
			helper.setText(content);
			
			FileSystemResource resource = new FileSystemResource(file);
			helper.addAttachment("附件：", resource);
			
			mailsender.send(msg);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}

}
