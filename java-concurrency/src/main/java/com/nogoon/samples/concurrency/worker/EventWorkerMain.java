package com.nogoon.samples.concurrency.worker;

import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class EventWorkerMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_Worker.xml");
		ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context.getBean("executorWithPoolSizeRange");
		WorkerFactory workerFactory = (WorkerFactory) context.getBean("workerFactory");
		
		StaticsWorker staticsWorker = (StaticsWorker) context.getBean("staticsWorker");
		Future<Integer> staticsFuture = staticsWorker.staticWorker(executor);
		
		Future<Long> mailFuture = workerFactory.findWorker(WorkerType.MAIL).processEvent();
		Thread.sleep(5000);
		
		Future<Long> approvalFuture = workerFactory.findWorker(WorkerType.APPROVAL).processEvent();
		Thread.sleep(5000);
		
		mailFuture.cancel(true);
		Thread.sleep(5000);
		
		Future<Long> calendarFuture = workerFactory.findWorker(WorkerType.CALENDAR).processEvent();
		Thread.sleep(5000);
		
		Future<Long> mailFuture1 = workerFactory.findWorker(WorkerType.MAIL).processEvent();
		Thread.sleep(5000);
		
		Future<Long> approvalFuture1 = workerFactory.findWorker(WorkerType.APPROVAL).processEvent();
		Thread.sleep(5000);
		
		Future<Long> calendarFuture1 = workerFactory.findWorker(WorkerType.CALENDAR).processEvent();
		Thread.sleep(5000);
		
		approvalFuture.cancel(true);
		Thread.sleep(5000);
		
		Thread.sleep(100000);
		
		staticsFuture.cancel(true);
		
		
		
		
		executor.shutdown();
	}

}
