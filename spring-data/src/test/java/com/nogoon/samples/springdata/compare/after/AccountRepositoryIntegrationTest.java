package com.nogoon.samples.springdata.compare.after;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
@ContextConfiguration("classpath:application-context-after.xml")
public class AccountRepositoryIntegrationTest extends AbstractCompareTest {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Test
	public void savesAccount() {

		Account account = accountRepository.save(new Account());
		assertThat(account.getId(), is(notNullValue()));
	}

	@Test
	public void findsCustomersAccounts() {

		Customer customer = customerRepository.findOne(1L);
		List<Account> accounts = accountRepository.findByCustomer(customer);

		assertFalse(accounts.isEmpty());
		assertThat(accounts.get(0).getCustomer(), is(customer));
	}
}
