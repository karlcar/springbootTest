package springBoot.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springBoot.service.EmailService;

@Controller
public class emailController {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/simple")
	@ResponseBody
	public String sendSimpleEmail() {
		emailService.sendSimpleEmail("498073785@qq.com", "你好，这是程序自动发的", "今天是个好日子");
		return "success";
	}
	
	@RequestMapping("/complex")
	@ResponseBody
	public String sendAttachmentEmail() {
		File file = new File("src/main/resources/static/MTL.txt");
		emailService.sendAttachementEmail("498073785@qq.com", "你好，这是带附件的邮件", "abcd",file);
		return "attachment success";
	}
	
	@RequestMapping("/template")
	@ResponseBody
	public String sendTemplateEmail() {
		emailService.sendTemplateEmail("498073785@qq.com", "模板邮件-test", "info.html");
		return "template success";
	}
	
	
}
