package springBoot.controller;


import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@RequestMapping("/index")
	public String toUpload() {
		return "upload";
	}
	
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public String uploadFile(MultipartFile file,HttpServletRequest request) {
		try {
			//创建文件在服务器端存放路径
			String dir = request.getServletContext().getRealPath("/upload");
			File uploadFile = new File(dir);	
			if(!uploadFile.exists()) {
				uploadFile.mkdirs();
			}
			//生成文件在服务器端的名字
			String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String fileName = UUID.randomUUID().toString() + fileSuffix;
			
			//创建文件存放的路径
			File files = new File(uploadFile + "/" + fileName);
			
			//开始上传
			file.transferTo(files);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		} 
		
		return "上传成功";
	}
	
	
	@RequestMapping("/indexs")
	public String toUploads() {
		return "uploads";
	}
	
	
	@RequestMapping(value="/upload/batch",method=RequestMethod.POST)
	@ResponseBody
	public String uploadFiles(MultipartFile[] file,HttpServletRequest request) {
		try {
			//创建文件在服务器端存放路径
			String dir = request.getServletContext().getRealPath("/upload");
			File uploadFile = new File(dir);	
			if(!uploadFile.exists()) {
				uploadFile.mkdirs();
			}
			//生成文件在服务器端的名字
			for(int i=0;i<file.length;i++) {
				
				String fileSuffix = file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf("."));
				String fileName = UUID.randomUUID().toString() + fileSuffix;
				
				//创建文件存放的路径
				File files = new File(uploadFile + "/" + fileName);
				
				//开始上传
				file[i].transferTo(files);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		} 
		
		return "上传成功";
	}
	
	
}
