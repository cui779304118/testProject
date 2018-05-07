package com.test.common;

public class InversePairs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public static int inversePairs(int[] arr){
		int len = arr.length;
		int result = 0;
		if(len == 0){
			return 0;
		}
		for(int i=0;i<len-1;i++){
			for(int j=i+1;j<len;j++){
				if(arr[j] < arr[i]){
					result++;
				}
			}
		}
		return result%1000000007;
	}
	public static void merge(int[] arr, int[] tmp, int st, int cen, int en ){
		int lst = st;
		int len = cen - 1;
		int rst = cen;
		int ren = en;
		int start = st;
		int length = en - st - 1;
		while(lst<=len && rst<=ren){
			if(arr[lst++] <= arr[rst++]){
				tmp[st++] = arr[lst++];
			}else{
				tmp[st++] = arr[rst++];
			}
		}
		while(lst<=len){
			tmp[st++]=arr[lst++];
		}
		while(rst<=ren){
			tmp[st++]=arr[rst++];
		}
		System.arraycopy(tmp, start, arr, start, length);
	}

}
