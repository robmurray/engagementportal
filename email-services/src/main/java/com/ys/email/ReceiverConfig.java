package com.ys.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

/**
 * Created by rob on 5/29/15.
 */
@Configuration
@ComponentScan({"com.ys.email", "com.ys.em.service"})
//@ImportResource({"classpath:META-INF/spring/integration/soifluke-imap-idle-config.xml"})
@EnableScheduling
public class ReceiverConfig {


    @Bean
    public Properties javaPOP3MailProperties() {
        Properties mailProperties = new Properties();
        mailProperties.put("mail.pop3.host", "");
        mailProperties.put("mail.pop3.port", "");

        // SSL setting
        mailProperties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.setProperty("mail.pop3.socketFactory.fallback", "false");
        mailProperties.setProperty("mail.pop3.socketFactory.port", "");


        return mailProperties;
    }

    @Bean
    public Properties javaIMAPMailProperties() {
        Properties mailProperties = new Properties();
        mailProperties.put("mail.store.protocol", "imaps");
        mailProperties.put("mail.imap.port", "993");
        mailProperties.put("mail.imaps.starttls.enable", "true");
        mailProperties.put("imaphost", "a2plcpnl0159.prod.iad2.secureserver.net");
        mailProperties.put("email", "soifluke@hanuman.io");
        mailProperties.put("password", "tAll137Tree");
        mailProperties.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.put("mail.imap.socketFactory.fallback", "false");
        mailProperties.put("mail.debug", "true");
        return mailProperties;
    }

    @Bean
    public Properties javaSMTPMailProperties() {
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailProperties.put("mail.smtp.port", "465");
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.user", "username@gmail.com");
        mailProperties.put("mail.smtp.password", "PASSWORD");
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.transport.protocol", "smtp");
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return mailProperties;
    }
}
