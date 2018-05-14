package com.test.thread;

public class LockMain {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockService service = new ReentrantLockService();
		LockThreadA ta = new LockThreadA(service);
		ta.setName("线程A");
		ta.start();
		
		LockThreadB tb = new LockThreadB(service);
		tb.setName("线程B");
		tb.start();
		
		Thread.sleep(3000);
		service.signalAll_A();
		Thread.sleep(3000);
		service.signalAll_B();
	}

}
