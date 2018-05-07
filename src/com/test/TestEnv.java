package com.test;


public class TestEnv {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(isDevRuntimeEnviroment());
//		System.out.println(new TestEnv().getClass().getClassLoader().getResource("").toString());
//		System.out.println(System.getenv("enviroment"));
//		String inviteCodeStr = "A" + String.format("%07d", 111111111);
//		System.out.println(inviteCodeStr);
		Integer num = 8888888;
		System.out.println(Integer.toBinaryString(-1));
		long num2 = System.currentTimeMillis();
		System.out.println(Long.toBinaryString(~(-1L << 5)));
	}
	
	public static boolean isDevRuntimeEnviroment(){
		String runtimeEnviroment = System.getenv("enviroment");
		return "DEV".equals(runtimeEnviroment);
	}
	

}
