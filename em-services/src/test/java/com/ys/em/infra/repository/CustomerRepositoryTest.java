package com.ys.em.infra.repository;


import com.ys.em.infra.domain.CustomerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        CustomerEntity c = new CustomerEntity();
        c.setName("bob");
      //  customerRepository.save(c);

       CustomerEntity c2 = customerRepository.findOne(new Long(1));

  //      assertTrue(c2.getName().equals(c.getName()));



    }
}
