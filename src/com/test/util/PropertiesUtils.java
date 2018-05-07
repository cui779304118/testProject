package com.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
	
	public static void main(String [] args){
		PropertiesUtils propUtil = new PropertiesUtils();
		String key="bootstrap.servers";
		String filePath="/file/producer.properties";
		propUtil.getValueByKey(key, filePath);
	}

	public String getValueByKey(String key,String filePath){
		Properties props = new Properties();
		InputStream inStream = null;
		try{
			 	inStream = getClass().getResourceAsStream(filePath);
			 	props.load(inStream);
			 	String value = props.getProperty(key);
			 	System.out.println(key + ":" + value);
			 	return value;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}finally{
			if(inStream != null){
				try {
					inStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
