package com.test.learn;

public class EnumTest {
	public static void main(String [] args){
		int i = 2;
		EnumType en = EnumType.getByKey(i);
		System.out.println(en.key + ":" + en.value);
	}
}
