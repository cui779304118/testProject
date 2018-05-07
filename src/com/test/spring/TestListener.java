package com.test.spring;

import java.util.concurrent.ExecutionException;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TestListener implements ApplicationListener<ApplicationEvent>{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof TestEvent){
			TestEvent testEvent = (TestEvent) event;
			System.out.println("监听到testEvent：" + testEvent.getMessage());
			while(true){
				try {
					for(int i=1;i<=4;i++){
						GuavaTest.getValue(testEvent.getKey() + i);
//						System.out.println("从Guava中取值：" + GuavaTest.getValue(testEvent.getKey() + i));
					}
					if(GuavaTest.getCounts() > 1000){
						break;
					}
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			System.out.println("context已经初始化！");
		}
		
		
	}
}