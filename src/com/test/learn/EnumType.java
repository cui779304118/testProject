package com.test.learn;

public enum EnumType {
	VIDEO(1,"视频"),AUDIO(2,"音频"),TEXT(3,"文本"),IMAG(4,"图片");
	
	int key;
	String value;
	
	EnumType(int key, String value){
		this.key = key;
		this.value = value;
	}
	
	public int getKey(){
		return key;
	}
	
	public String getValue(){
		return value;
	}
	
	public static EnumType getByKey(int key){
		for(EnumType en : EnumType.values()){
			if(key == en.key){
				return en;
			}
		}
		throw new IllegalArgumentException("No element matches " + key);  
	} 
	

}
