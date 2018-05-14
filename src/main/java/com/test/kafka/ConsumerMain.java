package com.test.kafka;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConsumerMain {

	/**
	 * @param args
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		try {
			Class consumerClass =  Class.forName("com.test.kafka.ConsumerTest");
			Constructor constructor =consumerClass.getConstructor(String.class);
			Object consumerTest = constructor.newInstance("appdata-fullidfa-dev");
			Method method = consumerClass.getDeclaredMethod("run");
			method.invoke(consumerTest);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
