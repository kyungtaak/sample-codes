package com.nogoon.samples.concurrency.raw;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * An asynchronous worker
 */
@Component("testWorker")
public class AsyncWorker implements Worker {

	@Async
	public Future<Long> work() {
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
		
		AsyncResult<Long> asyncResult = new AsyncResult<Long>(result);

		return asyncResult;
	}
}
