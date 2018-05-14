package com.test.util;

public class TestSingleton {
	public static void main(String [] args){
		Singleton singleton = Singleton.getInstance();
		singleton.showMessage();
		Singleton singleton2 = Singleton.getInstance();
	}

}
