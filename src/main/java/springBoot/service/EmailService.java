package springBoot.service;

public interface EmailService {
	//发送简单邮件	
	void sendSimpleEmail(String sendTo, String title, String content);
	
	
}
