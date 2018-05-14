package com.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class ConvertDate2Long {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String dateStr1 = "2018-1-5 11:35:00";
//		String dateStr2 = "2018-1-6 10:43:00";
//		try {
//			System.out.println(convert(dateStr1));
//			System.out.println(convert(dateStr2));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		int [] time = new int[]{2017,2,23,12,12,30,21};
		Long hour = getDiffHourByMills(time , 4);
		System.out.println(hour);
		System.out.println(new DateTime(hour).toString("yyyy-MM-dd HH:mm:ss SS"));
	}
	
	public static Long convert(String dateStr) throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sf.parse(dateStr);
		return date.getTime();
	}
	
	public static Long getDiffHourByMills(int [] time,int nextHour){
		DateTime dateTime = null;
		if(time.length !=6 && time.length !=7){
			return null;
		}
		int year = time[0];
		int month = time[1];
		int day = time[2];
		int hour = time [3];
		int minute = time [4];
		int second = time[5];
		
		if(time.length == 7){
			int mills = time[6];
			dateTime = new DateTime(year,month,day,hour,minute,second,mills);
			System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss SS"));
		}else{
			dateTime = new DateTime(year,month,day,hour,minute,second);
			System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
		}
		//年月日点分秒生成(参数依次是：年,月,日,时,分,秒,毫秒) 
		DateTime hourInTheDay = dateTime.plusHours(nextHour);
		return hourInTheDay.getMillis();
	}
	
}
