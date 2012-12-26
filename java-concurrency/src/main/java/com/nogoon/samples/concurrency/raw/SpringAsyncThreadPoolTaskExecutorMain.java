package com.nogoon.samples.concurrency.raw;

import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class SpringAsyncThreadPoolTaskExecutorMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_Executor_Test.xml");
		ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context.getBean("executorWithPoolSizeRange");
		
		Worker worker = (Worker) context.getBean("testWorker");
		
		int startCount = executor.getActiveCount();
		
		Future<Long> submit1 = worker.work();
		Future<Long> submit2 = worker.work();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int middleCount = executor.getActiveCount();
		
		submit1.cancel(true);
		submit2.cancel(true);

		System.out.println("1 Canceled! " + submit1.isCancelled());
		System.out.println("1 Done! " + submit1.isDone());
		
		System.out.println("2 Canceled! " + submit2.isCancelled());
		System.out.println("2 Done! " + submit2.isDone());
		
		int lastCount = executor.getActiveCount();

		System.out.println("Count: ");
		System.out.println(startCount);
		System.out.println(middleCount);
		System.out.println(lastCount);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Queue Info: " + executor.getThreadPoolExecutor().getQueue().size());
		System.out.println("Finally active count: " + executor.getActiveCount());
		
		executor.shutdown();

	}

}
