package com.nogoon.samples.springdata.repository;

import java.util.List;

import com.nogoon.samples.springdata.domain.User;

/**
 * Interface for repository functionality that ought to be implemented manually.
 * 
 * @author Oliver Gierke
 */
interface UserRepositoryCustom {

	/**
	 * Custom repository operation.
	 * 
	 * @return
	 */
	List<User> myCustomBatchOperation();
}
