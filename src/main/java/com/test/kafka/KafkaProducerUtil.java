package com.test.kafka;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

public class KafkaProducerUtil {
	private static Producer<String,String> instance ;
	
	public static Producer<String, String> getProducer(){
		if(instance == null){
			synchronized (KafkaProducer.class) {
				if(instance == null){
					instance = createProducer();
				}
			}
		}
		return instance;
	}

	private static Producer<String, String> createProducer() {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File("//file//producer.properties"));
			prop.load(inputStream);
			Producer<String, String> producer = new KafkaProducer<String,String>(prop);
			return producer;
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
