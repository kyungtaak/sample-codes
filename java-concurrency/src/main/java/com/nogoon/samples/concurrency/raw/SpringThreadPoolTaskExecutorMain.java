package com.nogoon.samples.concurrency.raw;

import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class SpringThreadPoolTaskExecutorMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_Executor_Test.xml");
		ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context.getBean("executorWithPoolSizeRange");
				
		
		Worker worker = (Worker) context.getBean("testWorker");
		
//		Callable<Long> worker = new Callable<Long>() {
//			public Long call() throws Exception {
//				long result = 0L;
//
//				System.out.println("working...");
//				while (true) {
//					if (Thread.currentThread().isInterrupted()) {
//						System.out.println("canceled...!");
//						//Thread.currentThread().interrupt();
//						break;
//					}
//					// Thread.sleep(1000); // simulates work
//					result++;
//					System.out.println("step " + result + "...");
//
//					if (result == Long.MAX_VALUE) {
//						break;
//					}
//				}
//
//				return result;
//			}
//		};
		
		int startCount = executor.getActiveCount();
		
		Future<Long> submit1 = worker.work();
		Future<Long> submit2 = worker.work();
		
//		Future<Long> submit1 = executor.submit(worker);
//		Future<Long> submit2 = executor.submit(worker);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int middleCount = executor.getActiveCount();
		
		submit1.cancel(true);
		submit2.cancel(true);

		System.out.println("1 Canceled! " + submit1.isCancelled());
		System.out.println("1 Done! " + submit1.isDone());
		
		System.out.println("2 Canceled! " + submit2.isCancelled());
		System.out.println("2 Done! " + submit2.isDone());
//		try {
//			System.out.println("Get! " + submit1.get());
//		} catch (CancellationException e1) {
//			// TODO Auto-generated catch block
//			//e1.printStackTrace();
//			System.out.println("ERROR: " + e1.getMessage());
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		int lastCount = executor.getActiveCount();

		System.out.println("Count: ");
		System.out.println(startCount);
		System.out.println(middleCount);
		System.out.println(lastCount);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Queue Info: " + executor.getThreadPoolExecutor().getQueue().size());
		System.out.println("Finally active count: " + executor.getActiveCount());
		
		executor.shutdown();

	}

}
