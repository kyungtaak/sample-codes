package com.nogoon.samples.concurrency.raw;

import java.util.concurrent.Future;

public interface Worker {
	Future<Long> work();
}
