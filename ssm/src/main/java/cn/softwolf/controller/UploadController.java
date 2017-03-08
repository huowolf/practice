package cn.softwolf.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import net.sf.json.JSONObject;

@Controller
public class UploadController {

	@Value("${file_server_path}")
	private String fileServer;
	
	@RequestMapping("/uploadPic")
	@ResponseBody
	public String uploadPic(MultipartFile mf) throws Exception{
		
		//定义上传后的文件名字
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			fileName += random.nextInt(10);
		}
		
		//获得文件的后缀名
		String oriFileName = mf.getOriginalFilename();
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
		
		//绝对路径
		String realPath = fileServer+"upload/"+ fileName + suffix;
		//相对路径
		String reletivePath = "upload/"+ fileName + suffix;
		//发送图片到另一台服务器
		Client client = new Client();
		WebResource wr = client.resource(realPath);
		wr.put(mf.getBytes());
		
		//返回数据
		JSONObject jo = new JSONObject();
		jo.put("realPath", realPath);
		jo.put("reletivePath", reletivePath);
		return jo.toString();
	}
	
}
