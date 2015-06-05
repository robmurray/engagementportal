package com.ys.email.scheduler;

import com.ys.email.service.IMAPSOImportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rob on 6/2/15.
 */
@Component
public class ReceiverScheduler {
    private static Logger logger = Logger.getLogger(ReceiverScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private IMAPSOImportService imapsoImportService;

    @Scheduled(fixedRate = 100*60*10,initialDelay=100*30)
    public void execute() {

        this.imapsoImportService.process();

    }
}

