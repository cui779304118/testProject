package com.test.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibMethodInterceptor implements MethodInterceptor {
	
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("cglib：method running before... ");
		Object result = methodProxy.invokeSuper(o, args);
		System.out.println("cglib：method running after... ");
		return result;
	}
}
