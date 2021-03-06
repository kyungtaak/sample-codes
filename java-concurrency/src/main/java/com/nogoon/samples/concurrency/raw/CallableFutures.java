package com.nogoon.samples.concurrency.raw;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutures {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		//ThreadPoolExecutor executor = Executors.newFixedThreadPool(10);
		Callable<Long> worker = new Callable<Long>() {
			public Long call() throws Exception {
				long result = 0L;

				System.out.println("working...");
				while (true) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("canceled...!");
						//Thread.currentThread().interrupt();
						break;
					}
					// Thread.sleep(1000); // simulates work
					result++;
					System.out.println("step " + result + "...");

					if (result == Long.MAX_VALUE) {
						break;
					}
				}

				return result;
			}
		};

		int startCount = Thread.activeCount();

		Future<Long> submit1 = executor.submit(worker);
		Future<Long> submit2 = executor.submit(worker);

		int middleCount = Thread.activeCount();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		submit1.cancel(true);
		submit2.cancel(true);

		System.out.println("Canceled! " + submit1.isCancelled());
		System.out.println("Done! " + submit1.isDone());
		try {
			System.out.println("Get! " + submit1.get());
		} catch (CancellationException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("ERROR: " + e1.getMessage());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int lastCount = Thread.activeCount();

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
		
		executor.shutdown();
		
		System.out.println("last count: " + Thread.activeCount());

	}

}
