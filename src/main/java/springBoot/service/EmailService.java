package springBoot.service;

import java.io.File;

public interface EmailService {
	//发送简单邮件	
	void sendSimpleEmail(String sendTo, String title, String content);
	
	//发送带附件的邮件
	void sendAttachementEmail(String sendTo, String title, String content, File file);
	
	//发送模板邮件
	void sendTemplateEmail(String sendTo, String title, String info);
}
