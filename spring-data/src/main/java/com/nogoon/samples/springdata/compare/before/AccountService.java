package com.nogoon.samples.springdata.compare.before;

import java.util.List;

import com.nogoon.samples.springdata.domain.Account;
import com.nogoon.samples.springdata.domain.Customer;

/**
 * Service interface for {@link Account}s.
 * 
 * @author Oliver Gierke
 */
public interface AccountService {

	/**
	 * Saves the given {@link Account}.
	 * 
	 * @param account
	 * @return
	 */
	Account save(Account account);

	/**
	 * Returns all {@link Account}s of the given {@link Customer}.
	 * 
	 * @param customer
	 * @return
	 */
	List<Account> findByCustomer(Customer customer);
}
