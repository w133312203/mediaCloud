package com.hm.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;


public class ImageUtil {
	
	public static String uploadImage(MultipartFile imageFile) {
		String fileName = imageFile.getOriginalFilename();
		int spotIndex = fileName.lastIndexOf(".");
		fileName = getSysTime()+fileName.substring(spotIndex);
		String filePath = DateUtil.getDatePath()+fileName;
		InputStream content;
		OSSClient client = new OSSClient(ApplicationUtil.END_POINT, ApplicationUtil.ACCESS_KEY_ID, ApplicationUtil.ACCESS_KEY_SECRET);
		try {
			content = imageFile.getInputStream();
			PutObjectResult result = client.putObject(ApplicationUtil.BUCKET_PIC, filePath, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			client.shutdown();
		}
		return filePath;
	}
	
	private synchronized static Long getSysTime() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return System.currentTimeMillis();
	}
	
}
