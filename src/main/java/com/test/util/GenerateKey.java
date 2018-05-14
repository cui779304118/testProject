package com.test.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.junit.Test;

public class GenerateKey {
	
	public static void main(String [] args){
		String commentId = "5acc1d5d6b05eb000139216c";
//		String replyId = "5ad04c0e6b05eb0001392182";
		Long timestamp = System.currentTimeMillis();
		String key = "R406xMbTmC6CtJPDijhO";
		String forKey = "commentId="+commentId+"timestamp="+timestamp+key;
		System.out.println(forKey);
		System.out.println(new GenerateKey().createMD5(forKey));
		//测试uuid
//		String uuid = UUID.randomUUID().toString();
//		System.out.println(uuid);
	}
	
	
	
	public void test() throws Exception{
	
		String IMEI1="862980037785524";
		String app_type1="ANDROID";
		
		String IMEI2="862052034761765";
		String app_type2="IOS";
		
		System.out.println("ԭʼIMEI�ţ� " + IMEI1);
		//System.out.println("���ܺ��muid: " + generateMuid(IMEI,app_type1));
		String muid1=generateMuid2(IMEI1,app_type1);
		System.out.println("���ܺ��muid1: " + muid1.equals("632643ae0b261f428937cd26fff84ebc") + muid1);
		
		String muid2=generateMuid2(IMEI2,app_type2);
		System.out.println("ԭʼIMEI�ţ� " + IMEI2);
		System.out.println("���ܺ��muid2: " + muid2.equals("120f594f88bce9ada0e0b36ffce5f7f9")+muid2);
		
//		Report_conv conv=new Report_conv();
//		conv.setApp_type("ANDROID");
//     	conv.setConv_time( "1422263664");
//     	conv.setMuid("0f074dc8e1f0547310e729032ac0730b");
//     	conv.setSign_key("08ebe39d34c421b8");
     	String app_type="ANDROID";
     	String conv_time="1422263664";
     	String muid="0f074dc8e1f0547310e729032ac0730b";
     	String sign_key="08ebe39d34c421b8";
     	String client_ip="";
		String enstr=generateEncstr(app_type, client_ip, conv_time, muid, sign_key);
		
		System.out.println("Encstr���Ϊ�� " + enstr);
		
	}
	
	public String createEncstr(Report_conv conv){
		String app_type=conv.getApp_type();
		String click_id=conv.getClick_id();
		String client_ip=conv.getClient_ip();
		String conv_time=conv.getConv_time();
		String muid=conv.getMuid();
		String sign_key=conv.getSign_key();
		
		if(app_type==null || conv_time==null || muid ==null || sign_key==null){
			System.out.println("�������������������������������������ݲ���ȷ��");
			return null;
		}else{
			if(click_id == null) click_id="";
			if(client_ip == null) client_ip="";
			StringBuffer concat=new StringBuffer();
			concat.append("app_type=" + app_type + "&");
			concat.append("click_id=" + click_id + "&");
			concat.append("client_ip=" + client_ip + "&");
			concat.append("conv_time=" + conv_time + "&");
			concat.append("muid=" + muid + "&");
			concat.append("sign_key=" + sign_key);
			String concatString=concat.toString();
			
			System.out.println("ƴ���ַ� " + concatString);
			return createMD5(concatString);
		}
		
	}
	public String generateMuid(String device_id,String app_type){
		if(device_id==null || app_type==null || device_id.isEmpty() || app_type.isEmpty()){
			return null;
		}else{
			if("ANDROID".equals(app_type)){
				device_id.toLowerCase();
			}else if("IOS".equals(app_type)){
				device_id.toUpperCase();
			}else{
				return null;
			}
			return createMD5(device_id);
		}
		
	}
	
	private String generateMuid2(String deviceId , String app_type) throws Exception{
    	
		String muid=null;
		if("ANDROID".equals(app_type)){
			deviceId.toLowerCase();
		}
		if("IOS".equals(app_type)){
			deviceId.toUpperCase();
		}
		muid=createMD5(deviceId);
		return muid;
}
	
	 private String generateEncstr(String app_type,String client_ip,String conv_time,String muid,String sign_key)
			    throws Exception{
			    	
						String click_id="";//���ֶ�ֵΪ�գ���Ϊ����muid���������
						StringBuffer concat=new StringBuffer();
						
						/*
						 * ƴ���ֶΣ����ڼ���muid
						 * */
						concat.append("app_type=" + app_type + "&");
						concat.append("click_id=" + click_id + "&");
						concat.append("client_ip=" + client_ip + "&");
						concat.append("conv_time=" + conv_time + "&");
						concat.append("muid=" + muid + "&");
						concat.append("sign_key=" + sign_key);
						String concatString=concat.toString();
						
						String encstr=createMD5(concatString);
						
						return encstr;
			    }

	private String createMD5(String concat) {
		if(concat==null || concat.isEmpty()){
			return null;
		}else{
			byte [] concatBytes=null;
			try {
				MessageDigest md5=MessageDigest.getInstance("MD5");
				md5.update(concat.getBytes("UTF-8"));
				concatBytes=md5.digest();
			} catch (NoSuchAlgorithmException e) {
				// TODO: handle exception
				System.out.println("�����㷨�����ڣ�");
			}catch(UnsupportedEncodingException e){
				System.out.println("��ݼ���ָ���ı����ʽ��֧�֣�");
			}
			return BytesConvertToHexString(concatBytes);
		}
	}

	private String BytesConvertToHexString(byte[] bytes) {
		StringBuffer sb=new StringBuffer();
		for(byte aByte:bytes){
			String s=Integer.toHexString(0xff&aByte);
			if(s.length()==1){
				sb.append("0"+s);
			}else{
				sb.append(s);
			}
		}
		return  sb.toString();
	}

}
