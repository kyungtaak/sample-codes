package com.nogoon.samples.springdata.compare.after;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nogoon.samples.springdata.domain.Account;
import com.nogoon.samples.springdata.domain.Customer;

/**
 * Repository to manage {@link Account} instances.
 * 
 * @author Oliver Gierke
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

	/**
	 * Returns all accounts belonging to the given {@link Customer}.
	 * 
	 * @param customer
	 * @return
	 */
	List<Account> findByCustomer(Customer customer);
}
