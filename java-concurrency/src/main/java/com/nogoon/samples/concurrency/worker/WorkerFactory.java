package com.nogoon.samples.concurrency.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("workerFactory")
public class WorkerFactory {
	
	@Autowired
	@Qualifier("mailEventWorker")
	EventWorker mailWorker;
	
	@Autowired
	@Qualifier("approvalEventWorker")	
	EventWorker approvalWorker;
	
	@Autowired
	@Qualifier("calendarEventWorker")
	EventWorker calendarWorker;
	
	public WorkerFactory() {
	}
		
	public EventWorker findWorker(WorkerType type) {
		EventWorker worker = null;
		
		switch (type) {
		case MAIL:
			worker = mailWorker;
			break;			
		case APPROVAL:
			worker = approvalWorker;
			break;
		case CALENDAR:
			worker = calendarWorker;
			break;
		default:
			worker = mailWorker;
			break;
		}
		
		return worker;
	}

}
