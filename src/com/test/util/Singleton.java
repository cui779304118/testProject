package com.test.util;


/**
 * 单例模式：DCL(Double Checked Locking)
 * 双检验锁 
 * 注意：
1、单例类只能有一个实例。
2、单例类必须自己创建自己的唯一实例。
3、单例类必须给所有其他对象提供这一实例。
 *
 *优点： 1、在内存里只有一个实例，减少了内存的开销，尤其是频繁的创建和销毁实例（比如管理学院首页页面缓存）。 2、避免对资源的多重占用（比如写文件操作）。
 */
public class Singleton {
	
	private static Singleton instance;
	
	private Singleton(){};
	
	public static Singleton getInstance(){
		if(instance ==  null){
			synchronized (Singleton.class) {
				if(instance == null){
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	public void showMessage(){
		System.out.println("hello！");
	}

}
