package com.test.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ListIteratorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>(){
			{
				add("abc");
				add("bcd");
				add("edf");
				add("dfa");
			}
		};
		
		System.out.println("the list size: " + list.size());
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			String temp = iterator.next();
			if(temp.equals("edf")){
				iterator.remove();
			}
		}
		
		System.out.println("after remove theSize: " + list.size());
	
		for(String s : list){
			System.out.print(s + " ");
		}
//		System.out.println("环境变量： " + System.getenv("name"));
		
		String [] listStr =(String []) list.toArray(new String[list.size()]);
		
		//String [] strArr = new String[]{"a","b","a","b"};
		
		String joinStr = StringUtils.join(listStr, ",");
		System.out.println( joinStr);
	}
	

}
