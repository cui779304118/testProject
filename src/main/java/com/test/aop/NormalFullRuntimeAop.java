package com.test.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NormalFullRuntimeAop {
	long beginTime;
	long endTime;
	int times = 0;
	Map<Integer,Long> runtimeMap = new HashMap<Integer,Long>();
	
	public Map<Integer,Long> getMap(){
		return runtimeMap;
	}
	
	@Pointcut("execution(** com.test.algorithm.NormalFull.test1(..))")
	public void test1(){};
	
	@Before("test1()")
	public void beforeRun(){
		times++;
	}
	
	@Around("test1()")
	public void aroundRun(ProceedingJoinPoint jp){
		try {
			beginTime = System.currentTimeMillis();
//			System.out.println("开始调用方法，时间为： " + beginTime );
			jp.proceed();
			endTime = System.currentTimeMillis();
			Long runTime = endTime - beginTime;
//			System.out.println("调用方法结束！总共耗时：" + (endTime - beginTime) + "毫秒！");
			runtimeMap.put(times, runTime);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@After("test1()")
//	public void afterRun(){
//		endTime = System.currentTimeMillis();
//		System.out.println("方法运行结束！总共耗时：" + (endTime - beginTime) + "毫秒！");
//	}
}
