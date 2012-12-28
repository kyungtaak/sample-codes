package com.nogoon.samples.springdata.compare.before;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.nogoon.samples.springdata.compare.AbstractCompareTest;
import com.nogoon.samples.springdata.domain.Account;
import com.nogoon.samples.springdata.domain.Customer;

/**
 * @author Oliver Gierke
 */
@ContextConfiguration("classpath:application-context-before.xml")
public class AccountServiceIntegrationTest extends AbstractCompareTest {

	@Autowired
	AccountService accountService;

	@Autowired
	CustomerService customerService;

	@Test
	public void savesAccount() {

		Account account = accountService.save(new Account());
		assertThat(account.getId(), is(notNullValue()));
	}

	@Test
	public void testname() throws Exception {

		Customer customer = customerService.findById(1L);

		List<Account> accounts = accountService.findByCustomer(customer);

		assertFalse(accounts.isEmpty());
		assertThat(accounts.get(0).getCustomer(), is(customer));
	}
}
