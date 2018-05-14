package com.test.spring;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
@Component
public class GuavaTest {
	private static LoadingCache<String,String> cache;
	private static int count;
	
	private GuavaTest(){
		cache = CacheBuilder.newBuilder().maximumSize(5).refreshAfterWrite(1, TimeUnit.SECONDS).build(
						new CacheLoader<String, String>() {
							public String load(String key) throws Exception {
								System.out.println(key + " 从guava中load值 " + (++count) );
								String content = "";
								switch(key){
									case "key1":content = "value1";break;
									case "key2":content = "value2";break;
									case "key3":content = "value3";break;
									case "key4":content = "value4";break;
								}
								return content;
							}
						}
		);
	}
	public static LoadingCache<String, String> getCache(){
		return cache;
	}
	
	public static String getValue(String key) throws ExecutionException {
		return cache.get(key);
	}
	public static int getCounts(){
		return count;
	}
}
