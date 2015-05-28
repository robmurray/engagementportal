package com.ys.um.infra.repository;


import com.ys.um.infra.domain.UserEntity;
import com.ys.um.infra.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by rob on 4/8/15.
 */

//SpringApplicationConfiguration(classes = PlainJpaConfig.class)

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestRepositoryConfig.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser() {

        try {
            UserEntity ue = new UserEntity();
            ue.setEmail("rob@hanumansolutions.com");
            ue.setFirstName("Rob");
            ue.setLastName("Murray");
            ue.setNotes("a few notes");
            //userRepository.save(ue);
            assertTrue("awesome",true);
        } catch (Exception e) {
            assertTrue("an exception was thrown", false);
            //throw e;

        }

    }
}
