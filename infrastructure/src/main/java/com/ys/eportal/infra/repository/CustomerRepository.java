package com.ys.eportal.infra.repository;

import java.util.Set;
import com.ys.eportal.infra.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByCustomerId(int customerId);


}