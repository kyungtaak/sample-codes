package com.nogoon.samples.concurrency.raw;

import java.util.concurrent.Callable;

public class CallableImpl implements Callable<Long> {

	public Long call() throws Exception {
		System.out.println("RUN");
		long sum = 0;
	    for (long i = 0; i <= 100; i++) {
	      sum += i;
	    }
	    System.out.println("RETURN : " + sum);
	    return sum;
	}

}
