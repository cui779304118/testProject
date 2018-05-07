package com.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class NormalFull {
	public static int totalStep = 9;
	public static int[] arr = new int[totalStep];
	public static int[] repeat = new int[totalStep];
	public static int[] sourArr = new int[]{1,2,3,4,5,6,7,8,9};
	public static List<String> resultList = new ArrayList<String>();

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
//		test1(0);
//		System.out.println("总共有：【" + resultList.size() + "】种排列！");
//		System.out.println(Arrays.toString(resultList.toArray()));
//	}
	public static List<String> fullV1(){
		List<String> resultList = new ArrayList<String>();
		int[] arr = new int[5];
		for(arr[1] = 1; arr[1]<5; arr[1]++){
			for(arr[2] = 1; arr[2]<5; arr[2]++){
				for(arr[3] = 1; arr[3]<5; arr[3]++){
					for(arr[4] = 1; arr[4]<5; arr[4]++){
						boolean isRepeat = false;
						int [] temp = new int[5];
						for(int i=1;i<5;i++){
							temp[arr[i]]++;
						}
						for(int i=1;i<5;i++){
							if(temp[i]>1){
								 isRepeat = true;
								 break;
							}
						}
						if(!isRepeat){
							String resultNumStr = arr[1] +""+ arr[2] +""+ arr[3] +""+ arr[4] + "";
							resultList.add(resultNumStr);
						}
					}
				}
			}
		}
		return resultList;
	}
	
	public static void fullV2Main(){
		fullV2(0);
	}
	
	public static void fullV3Main(){
		fullV3(0);
	}
	
	public static void fullV2(int step){
		if(step >= totalStep){
			boolean isRepeat = false;
			int[] res = new int[totalStep];
			for(int i=0; i<totalStep;i++){
				int temp = ++res[arr[i]];
				if(temp >1 ) isRepeat = true;
			}
			if(!isRepeat){
				String temp = array2String(arr);
				System.out.println(temp);
				resultList.add(temp);
			}
			return;
		}
		for(int i=0;i<totalStep;i++){
			arr[step] = sourArr[i];
			fullV2(step + 1);
		}
	}
	
	/**
	 * 最优全排列方法
	 * @param step
	 */
	public static void fullV3(int step){
		if(step >= totalStep){
			String temp = array2String(arr);
			System.out.println(temp);
			resultList.add(temp);
			return;
		}
		for(int i=0;i<totalStep;i++){
			if(repeat[i]==0){//当前数还没有被全排列，
				repeat[i] = 1;//标记当前数已经被全排列
				arr[step] = sourArr[i];
				fullV3(step + 1);
				repeat[i] = 0;//标记当前数不全排，此时，当前数已经全排完毕
			}
		}
	}
	
	public static String array2String(int[] arr){
		StringBuilder builder = new StringBuilder();
		for(int i : arr){
			builder.append(i);
		}
		return builder.toString();
	}
	/**
	 * 应用例子：***+***=*** 1-9任意填，能满足这个式子的有几种?
	 * @param arr
	 * @return
	 */
	public  void test1(int step){
		if(step >= totalStep){
			int a = createNum(arr, 0, 3);
//			System.out.println(a);
			int b = createNum(arr, 3, 3);
//			System.out.println(b);
			int c = createNum(arr, 6, 3);
//			System.out.println(c);
			if(a+b==c){
				String tempStr = a + "+" + b + "=" + c;
//				System.out.println(tempStr);
				resultList.add(tempStr);
				return;
			}
		}
		for(int i=0;i<totalStep;i++){
			if(repeat[i]==0){//当前数还没有被全排列，
				repeat[i] = 1;//标记当前数已经被全排列
				arr[step] = sourArr[i];
				test1(step + 1);
				repeat[i] = 0;//标记当前数不全排，此时，当前数已经全排完毕
			}
		}
	}
	
	private  int createNum(int[] arr,int offset, int count) throws IndexOutOfBoundsException{
		int length = arr.length;
		if((offset + count) > length){
			throw new IndexOutOfBoundsException();
		}
		int result = 0;
		int b = count;
		for(int i=offset;i<(offset+count);i++){
			int temp = (int)Math.pow(10, --b);
			result += arr[i]*temp;
		}
		return result;
	}
	
}
