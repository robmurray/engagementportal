package com.ys.eportal.infra.repository;



import com.ys.eportal.infra.TestRepositoryConfig;
import com.ys.eportal.infra.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
        Customer c = new Customer();
        c.setName("bob");
        customerRepository.save(c);

        Customer c2 = customerRepository.findByCustomerId(c.getCustomerId());

        assertTrue(c2.getName().equals(c.getName()));



    }
}
