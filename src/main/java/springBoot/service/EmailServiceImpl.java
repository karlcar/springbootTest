package springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

}
