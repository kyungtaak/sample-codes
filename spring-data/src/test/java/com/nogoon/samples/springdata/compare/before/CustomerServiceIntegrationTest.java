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
import com.nogoon.samples.springdata.domain.Customer;

/**
 * @author Oliver Gierke
 */
@ContextConfiguration("classpath:application-context-before.xml")
public class CustomerServiceIntegrationTest extends AbstractCompareTest {

	@Autowired
	CustomerService repository;

	@Test
	public void findsAllCustomers() throws Exception {

		List<Customer> result = repository.findAll();

		assertThat(result, is(notNullValue()));
		assertFalse(result.isEmpty());
	}

	@Test
	public void findsPageOfMatthews() throws Exception {

		List<Customer> customers = repository.findByLastname("Matthews", 0, 2);

		assertThat(customers.size(), is(2));
	}

	@Test
	public void findsCustomerById() throws Exception {

		Customer customer = repository.findById(2L);

		assertThat(customer.getFirstname(), is("Carter"));
		assertThat(customer.getLastname(), is("Beauford"));
	}
}
