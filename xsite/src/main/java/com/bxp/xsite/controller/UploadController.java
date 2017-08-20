package com.bxp.xsite.controller;

import java.io.*;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.bxp.xsite.common.utils.file.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/upload")
public class UploadController {
	public static final String HOST = "http://47.94.19.49:8080/xsite";
	//https://www.kancloud.cn/wangfupeng/wangeditor/65748
	@RequestMapping("/uploadPicture")
	public void toItemIndex(HttpServletRequest request,
							Writer writer) throws IOException{
		//将request转换成复杂类型
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
		//获取文件
		Map<String, MultipartFile> fileMap = mr.getFileMap();
		Set<String> keySet = fileMap.keySet();
		Iterator<String> it = keySet.iterator();
		String fileKey = it.next();

		MultipartFile file = fileMap.get(fileKey);

		//获取文件名
		String clientFileName = file.getOriginalFilename();

		System.out.println("文件名" + clientFileName);
		//获取文件的字节数组
		byte[] bs = file.getBytes();
		System.out.println("文件大小" + bs.length);
		//获取文件后缀名
		String suffix = FileUtil.getFileNameSuffix(clientFileName);
		//获取唯一文件名
		String serviceFileName = FileUtil.getFileName() + suffix;
		//获取文件的相对路径
		String relativePath = FileUtil.getPath("upload", serviceFileName);
		//获取fenian系统
		String fileSystem = request.getSession().getServletContext().getRealPath("");
		//获取绝对路径
		String relaPath = fileSystem  + relativePath;
//		//创建jersy客户端
//		Client client = Client.create();
//		//创建web资源对象
//		WebResource wr = client.resource(relaPath);
//		//上传文件
//		wr.put(bs);
		//创建目录
		new File(relaPath).mkdirs();

		File picture = new File(relaPath + serviceFileName);
		picture.createNewFile();
		FileOutputStream out = new FileOutputStream(picture);
		out.write(bs);

		JSONObject json = new JSONObject();
		json.fluentPut("relaPath", relaPath + serviceFileName);
		json.fluentPut("relativePath", relativePath + serviceFileName);
		String url = HOST + File.separator + relativePath + serviceFileName;
		json.fluentPut("url", url);
		writer.write(json.toString());
	}

}
