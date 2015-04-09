package com.ys.eportal.repository;


import com.ys.eportal.infra.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by rob on 4/8/15.
 */

//SpringApplicationConfiguration(classes = PlainJpaConfig.class)

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestRepositoryConfig.class)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void retrieveCustomer(){
        /*CustomerEntity c = new CustomerEntity();
        c.setName("bob");
        customerRepository.save(c);

        CustomerEntity c2 = customerRepository.findByCustomerId(c.getCustomerId());

        assertTrue(c2.getName().equals(c.getName()));
*/


    }
}
