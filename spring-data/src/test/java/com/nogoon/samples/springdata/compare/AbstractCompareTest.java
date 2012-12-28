package com.nogoon.samples.springdata.compare;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.BeforeTransaction;

/**
 * @author Oliver Gierke
 */
public abstract class AbstractCompareTest extends AbstractTransactionalJUnit4SpringContextTests {

	@BeforeTransaction
	public void setupData() throws Exception {
		if (countRowsInTable("Customer") == 0) {
			executeSqlScript("classpath:data.sql", false);
		}
	}
}
