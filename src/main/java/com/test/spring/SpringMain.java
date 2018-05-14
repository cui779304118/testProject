package com.test.spring;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.algorithm.NormalFull;
import com.test.aop.NormalFullRuntimeAop;

public class SpringMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
//		TestProperty testProperty = (TestProperty) context.getBean("testProperty");
//		System.out.println(testProperty.getProperty("datasource.username"));
//		System.out.println(testProperty.toString());
		NormalFull full = (NormalFull) context.getBean("normalFull");
		NormalFullRuntimeAop aop = context.getBean("normalFullRuntimeAop",NormalFullRuntimeAop.class);
		for(int i =0;i<5;i++){
			full.test1(0);
			System.out.println("总共有：【" + full.resultList.size() + "】种排列！");
			System.out.println(Arrays.toString(full.resultList.toArray()));
		}
		Map<Integer,Long> resultMap = aop.getMap();
		Set<Entry<Integer, Long>> entrySet = resultMap.entrySet();
//		Set<Integer> keySet = resultMap.keySet();
//		for(Integer key : keySet){
//			System.out.println("第【"+key+"】次运算，计算时间为：【"+resultMap.get(key)+"】毫秒!");
//		}
		for(Entry<Integer, Long> entry : entrySet){
			System.out.println("第【"+entry.getKey()+"】次运算，计算时间为：【"+entry.getValue()+"】毫秒!");
		}
	} 
}
