package com.test.thread;

public class LockThreadB extends Thread {
	ReentrantLockService service = null;
	public LockThreadB(ReentrantLockService service){
		this.service = service;
	}
	
	public void run(){
		service.awaitB();
	}
}
