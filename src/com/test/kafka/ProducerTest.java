package com.test.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;

public class ProducerTest {
	
	public static void main(String [] args){
		ProducerTest producerTest = new ProducerTest();
		System.out.println("===========开始发送消息===========");
		String topic = "appdata-fullidfa-dev";
		producerTest.sendMsg(topic);
		System.out.println("===========发送消息成功===========");
	}
	
	public void listPartions(Producer<String, String> producer , String topic){
		List<PartitionInfo> partionInfoList = new ArrayList<PartitionInfo>();
		partionInfoList = producer.partitionsFor(topic);
		for(PartitionInfo partition : partionInfoList){
			System.out.println(partition);
		}
	}
	
	public void sendMsg(String topic){
		Properties pros = getPropertiesByFile("/file/producer.properties");
		Producer<String,String> produce = new KafkaProducer<String, String>(pros);
//		Producer<String,String> produce = KafkaProducerUtil.getProducer();
//		String recordKey = "first key";
		String recordValue = "{first:record}";
		ProducerRecord<String,String> precord = new ProducerRecord<String, String>(topic,recordValue);
//		produce.send(precord,new CallbackInfo());
		produce.send(precord);
		listPartions(produce, topic);
	}
	
	private class CallbackInfo implements Callback{
		 public void onCompletion(RecordMetadata metadata, Exception e) {
             if(e != null) {
                 e.printStackTrace();
             } else {
                 System.out.println("The offset of the record we just sent is: " + metadata.offset());
             }
         }
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
