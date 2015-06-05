package com.ys.email.service;

import com.ys.email.ReceiverConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by rob on 5/29/15.
 */
//@ContextConfiguration("/META-INF/spring/integration/inbound-gateway-config.xml")

//@ContextConfiguration(classes = TestReceiverConfig.class)
@ContextConfiguration(classes = ReceiverConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class IMAPSOImportReceiverTest {
    private static Logger logger = Logger.getLogger(IMAPSOImportReceiverTest.class);

    @Autowired
    private IMAPSOImportService imapService;


    @Test
    public void receiverTest(){
        this.imapService.process();
    }
}
