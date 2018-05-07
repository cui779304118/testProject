package com.test.spring;

import org.springframework.context.ApplicationEvent;

public class TestEvent extends ApplicationEvent {
	private String message;
	private String key;

	public TestEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	public TestEvent(Object source, String message, String key) {
		super(source);
		this.message = message;
		this.setKey(key);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
