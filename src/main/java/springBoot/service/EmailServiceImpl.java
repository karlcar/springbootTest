package springBoot.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import springBoot.email.EmailConfig;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private EmailConfig emailconfig;
	
	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
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
	//发送带附件的邮件
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
	
	@Override
	//发送模板邮件
	public void sendTemplateEmail(String sendTo, String title,String info) {
		MimeMessage msg = mailsender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setFrom(emailconfig.getEmailFrom());
			helper.setTo(sendTo);
			helper.setSubject(title);
			
			//封装使用的模板
			Map<String,Object> model = new HashMap<>();
			model.put("username", "小黄");
			
			//创建模板并且将模板的数据塞进去
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(info);
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			
			helper.setText(html,true);
			
			mailsender.send(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
