package com.test.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ConsumerTest {
	private final String topic;
	private final KafkaConsumer<String, String> consumer;
	
	public ConsumerTest(String topic){
		this.topic = topic;
		consumer =(KafkaConsumer<String, String>) createConsumer();
	}
	
	public void run(){
		while(true){
			try {
				ConsumerRecords<String,String> consumerRecords = consumer.poll(100);
				for(ConsumerRecord<String, String> consumerRecord : consumerRecords){
					String message = consumerRecord.value();
					try {
//						JSONObject json = (JSONObject) JSON.parse(message);
//						System.out.println(json.getString("idfa"));
						System.out.println(message);
					} catch (Exception e) {
						System.out.println("获取消息异常！");
					}
				}
			} catch (Exception e) {
				System.out.println("接受消息异常！");
			}
		}
	}
	
	private Consumer<String, String> createConsumer(){
		Properties prop = getPropertiesByFile("/file/consumer.properties");
		System.out.println(prop.getProperty("group.id"));
		Consumer<String,String> consumer = new KafkaConsumer<String,String>(prop);
		consumer.subscribe(Collections.singletonList(topic));
		return consumer;
	}
	
	private Properties getPropertiesByFile(String filePath){
		Properties pro = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getResourceAsStream(filePath);
			pro.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return pro;
	}
}
