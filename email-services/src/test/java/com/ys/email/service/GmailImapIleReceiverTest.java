package com.ys.email.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by rob on 5/29/15.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = TestRepositoryConfig.class)
public class GmailImapIleReceiverTest {
    private static Logger logger = Logger.getLogger(GmailImapIleReceiverTest.class);
   // @Test
    public void receiverTest(){

        ApplicationContext ac = new ClassPathXmlApplicationContext("/META-INF/spring/integration/soifluke-imap-idle-config.xml");
        DirectChannel inputChannel = ac.getBean("receiveChannel", DirectChannel.class);


        inputChannel.subscribe(new MessageHandler() {
            public void handleMessage(Message<?> message) throws MessagingException {
                logger.info("Message: " + message);
            }
        });

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("wait a minute");
    }
}
