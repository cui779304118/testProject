package com.test.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

public class Base64Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String str = "cuiwei";
//		String base64Str = encodeBase64(str);
//		System.out.println(base64Str);
//		System.out.println(decodeBase64(base64Str));
		String filePath = "C:\\Users\\Lebron\\Desktop\\网易入职\\崔伟+证件照.jpg";
//		String base64 = encodePicture(filePath);
//		System.out.println(generatePicByBase64(base64, "base64"));
		System.out.println(new String(readFile2ByteArr(filePath)));
	}
	
	public static String encodeBase64(String str){
		return Base64Utils.encodeToString(str.getBytes());
	}
	
	public static String decodeBase64(String str){
		return new String(Base64Utils.decodeFromString(str));
	}
	
	public static String encodePicture(String filePath){
		File file = null;
		FileInputStream fins = null;
		try {
			file = new File(filePath);
			fins = new FileInputStream(file);
			byte [] buffer = new byte[fins.available()];
			fins.read(buffer);
			return Base64Utils.encodeToString(buffer);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(fins!=null){
				try {
					fins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static boolean generatePicByBase64(String base64, String picName){
		if(StringUtils.isEmpty(base64) || StringUtils.isEmpty(picName)){
			return false;
		}
		File file = null;
		OutputStream os = null;
		try {
			file = new File("C://Users//weicui004746//Desktop//" + picName + ".jpg");
			os = new FileOutputStream(file);
			byte [] data = Base64Utils.decodeFromString(base64);
			os.write(data);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(os!=null){
				try {
					os.close();
					return true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public static byte[] readFile2ByteArr(String fileName){
		if(StringUtils.isEmpty(fileName)){
			return null;
		}
		File file = null;
		InputStream ins = null;
		ByteArrayOutputStream ous = null;
		try {
			file = new File(fileName);
			ins = new FileInputStream(file);
			ous = new ByteArrayOutputStream();
			int len = 0;
			byte [] buffer = new byte[1024];
			while((len = ins.read(buffer)) != -1){
				ous.write(buffer, 0, len);
			}
			return ous.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
