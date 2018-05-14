package com.test.learn;

public class FastSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] a = new int[]{13,13,13,25,643,1,341,343,33};
		fastSort(a,0,a.length-1);
		printArray(a);
	}
	
	public static void fastSort(int [] array, int st, int en){
		if(st>=en){return;}
		int i = st+1,j = en;
		int mid = st;
		while(true){
			for(;j>st;j--){
				if(array[j] < array[st]){
					break;
				}
			}
			if(j==st+1){
				int t = array[st];
				array[st] = array[st+1];
				array[st+1] = t;
				mid = st + 1;
				break;
			}
			if(j==st){
				mid = st;
				break;
			}
			for(;i<=en;i++){
				if(array[i] > array[st]){
					break;
				}
			}
			if(j<=i){
				int t = array[st];
				array[st] = array[j];
				array[j] = t;
				mid = j;
				break;
			}
			int t = array[i];
			array[i] = array[j];
			array[j] = t;
			printArray(array);
		}
		fastSort(array,st,mid - 1);
		fastSort(array,mid+1,en);
	}
	
	public static void printArray(int [] array){
		System.out.println("");
		for(int i : array){
			System.out.print(i + " ");
		}
	}

}
