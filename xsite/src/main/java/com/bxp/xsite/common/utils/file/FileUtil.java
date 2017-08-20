package com.bxp.xsite.common.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class FileUtil {
	
	public static final String FILE_SYSTEM = "file_system";
	public static String getSystem(String key){
//		InputStream inStream = FileUtil.class.getClassLoader().getResourceAsStream("system.properties");
		Properties properties = System.getProperties();
//		try {
////			properties.load(inStream);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return properties.getProperty(key);
	}
	
	public static String getFileName(){
		return UUID.randomUUID().toString() +"-"+ Long.toHexString(System.currentTimeMillis()).toLowerCase();
	}
	
	public static String getPath(String path, String fileName){
		int hashCode = fileName.hashCode();
		int dir1 = hashCode&15;//0xf=15
		int dir2 = (hashCode>>4)&0xf;
		return path + File.separator + dir1 + File.separator + dir2 + File.separator;
	}
	
	public static String getFileNameSuffix(String fileName){
		if(fileName != null && fileName.length() > 0 && fileName.contains("."))
			return fileName.substring(fileName.lastIndexOf("."));
		else
			return "";
	}
}
