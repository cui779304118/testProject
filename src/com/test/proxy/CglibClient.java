package com.test.proxy;

import net.sf.cglib.proxy.Enhancer;

public class CglibClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(CglibTestImpl.class);
		enhancer.setCallback(new CglibMethodInterceptor());
		CglibTestImpl impl = (CglibTestImpl) enhancer.create();
		impl.sayHello();
	}
}
