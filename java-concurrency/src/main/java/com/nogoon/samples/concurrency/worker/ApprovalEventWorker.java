package com.nogoon.samples.concurrency.worker;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component("approvalEventWorker")
public class ApprovalEventWorker implements EventWorker {
	
	Logger logger = LoggerFactory.getLogger("APPROVAL_WORKER");
	
	@Async
	public Future<Long> processEvent() {
		logger.info("APPROVAL EVENT WORKER START ========>");
		
		long result = 0L;
		while (true) {
			if (Thread.currentThread().isInterrupted()) {
				logger.info("APPROVAL EVENT WORKER CANCELED ========> INTERRUPTED");
				break;
			}
			// Thread.sleep(1000); // simulates work
			result++;
			logger.debug("APPROVAL EVENT WORKER : RESULT " + result + "...");

			if (result == Long.MAX_VALUE) {
				logger.info("APPROVALL EVENT WORKER CANCELED ========> MAX VALUES");
				break;				
			}
		}
		
		AsyncResult<Long> asyncResult = new AsyncResult<Long>(result);

		logger.info("APPROVAL EVENT WORKER END ========>");
		
		return asyncResult;
	}	
}
