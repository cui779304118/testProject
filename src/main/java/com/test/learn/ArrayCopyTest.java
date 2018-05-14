package com.test.learn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayCopyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String,Integer> map = new HashMap<String,Integer>(){
			{
				put("key1",1);
				put("key2",2);
				put("key3",3);
				put("key4",4);
				put("key5",5);
				put("key6",6);
			}
		};
		
		String[] keys = map.keySet().toArray(new String[0]);
		String[] keysCopy = Arrays.copyOf(keys, keys.length-3);
		for(int i=0;i<keysCopy.length;i++){
			System.out.println(keysCopy[i]+" ");
		}
		String [] keysCopy2 = new String[10];
		System.arraycopy(keysCopy, 0, keysCopy2, 0, 3);
		for(int i=0;i<keysCopy2.length;i++){
			System.out.println(keysCopy2[i]+" ");
		}
	}
	
}
