package com.test.thread;

public class LockThreadA extends Thread {
	ReentrantLockService service = null;
	public LockThreadA(ReentrantLockService service){
		this.service = service;
	}
	
	public void run(){
		service.awaitA();
	}
}
