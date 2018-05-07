package com.test.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class RegexTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String mail = "cui779304118@126.com";
		String mailRegex = "^[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-Z0-9]-*){1,}\\.){1,3}[a-zA-Z\\-]{1,}$";
		String phone = "13778875124";
		String phoneRegex = "^1[3|4|5|8][0-9]{9}$";
		String userRegex = "^([a-zA-z])([a-zA-Z0-9_-]){7,12}$";
		String username = "cui77999990000000000";
		System.out.println(validate(userRegex,username));
	}
	
	public static boolean validate(String regex,String text){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
//		System.out.println(matcher.group(1));
		if(!matcher.find(1)){
			System.out.println("用户名应该以字母开头！");
//			return false;
		}
//		System.out.println(matcher.group(2));
		if(!matcher.find(2)){
			System.out.println("用户名应该介于8位到12位之间！");
			return false;
		}
		return matcher.matches();
	}

}
