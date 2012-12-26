package com.nogoon.samples.concurrency.worker;

import java.util.concurrent.Future;

public interface EventWorker {
	Future<Long> processEvent();
}
