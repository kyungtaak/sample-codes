package com.nogoon.samples.concurrency.worker;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component("staticsWorker")
public class StaticsWorker {
	
	Logger logger = LoggerFactory.getLogger("STAT_WORKER");
	
	@Async
	public Future<Integer> staticWorker(ThreadPoolTaskExecutor executor) {
		while(!Thread.currentThread().isInterrupted()) {			
			logger.info("----------------------------------");
			logger.info("Total Thread  : " + executor.getPoolSize());
			logger.info("Active Thread : " + executor.getActiveCount());
			logger.info("Queue Size    : " + executor.getThreadPoolExecutor().getQueue().size());
			logger.info("----------------------------------");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}
		}
		
		return new AsyncResult<Integer>(0);
	}
}
