//package com.test.kafka;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.sohu.bigdata.news.async.handle.consumer.constant.PointConstants;
//import com.sohu.bigdata.news.async.handle.consumernew.handler.impl.ProducerRun;
//import com.sohu.bigdata.news.async.handle.consumernew.handler.impl.notice.NoticeConstants;
//import com.sohu.bigdata.news.async.handle.consumernew.handler.impl.notice.NoticeProducer;
//
//public class SendMsg {
//	public static void main(String[] args) throws InterruptedException{
//    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-mongo.xml");
//    	NoticeProducer noticeProducer = (NoticeProducer) context.getBean("noticeProducer");
//    	String userId = "5a9e40ae2c346400010d44a2" ;
//    	ProducerRun run = new ProducerRun(noticeProducer, userId);
//    	Thread pT = new Thread(run);
//    	System.out.println("任务开始" + "===时间为： " + System.currentTimeMillis());
//    	pT.start();
//    	pT.join();
//    	System.out.println("任务结束" + "===时间为： " + System.currentTimeMillis());
//    }
//}
//class ProducerRun implements Runnable{
//	NoticeProducer noticeProducer = null;
//	String userId = null;
//	
//	public ProducerRun(NoticeProducer noticeProducer, String userId){
//		this.noticeProducer = noticeProducer;
//		this.userId = userId;
//	}
//	public void run(){
//		while(true){
//			Long startTime = System.currentTimeMillis();
//			String[] noticeParams = new String[]{NoticeConstants.SYSTEM_MESSAGE_MAP.get(PointConstants.PIC_NOPASS_CODE)};
//			noticeProducer.sendNoticeParams2Consumer(userId, PointConstants.SYSTEM_MESSAGE, noticeParams);
//			System.out.println(System.currentTimeMillis() + " 发送成功！");
//			try {
//				Thread.sleep(120000);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			Long endTime = System.currentTimeMillis();
//			if((endTime - startTime)>3600000){
//				break;
//			}
//		}
//	}
//}
