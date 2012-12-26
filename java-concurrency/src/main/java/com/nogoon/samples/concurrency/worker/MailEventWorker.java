package com.nogoon.samples.concurrency.worker;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component("mailEventWorker")
public class MailEventWorker implements EventWorker {
	
	Logger logger = LoggerFactory.getLogger("MAIL_WORKER");
	
	@Async
	public Future<Long> processEvent() {
		logger.info("MAIL EVENT WORKER START ========>");
		
		long result = 0L;
		while (true) {
			if (Thread.currentThread().isInterrupted()) {
				logger.info("MAIL EVENT WORKER CANCELED ========> INTERRUPTED");
				break;
			}
			// Thread.sleep(1000); // simulates work
			result++;
			logger.debug("MAIL EVENT WORKER : RESULT " + result + "...");

			if (result == Long.MAX_VALUE) {
				logger.info("MAIL EVENT WORKER CANCELED ========> MAX VALUES");
				break;				
			}
		}
		
		AsyncResult<Long> asyncResult = new AsyncResult<Long>(result);
		logger.info("MAIL EVENT WORKER END ========>");
		return asyncResult;
	}	
}
