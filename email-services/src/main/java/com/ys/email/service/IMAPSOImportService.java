package com.ys.email.service;

import com.sun.mail.imap.IMAPStore;

import com.ys.em.service.ImportService;
import com.ys.em.service.converter.CSVConversionFailureException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by rob on 6/1/15.
 */

@Service
public class IMAPSOImportService {

    private static Logger logger = Logger.getLogger(IMAPSOImportService.class);
    @Autowired
    private Properties javaIMAPMailProperties;

    @Autowired
    private ImportService importService;

    private String saveDirectory;

    private String supportedExtension = ".csv";

    private String validSenderList = "rob@yarrumsoftware.com,rjmurray99@gmail.com";

    // used for testing
    private boolean allowAllSender = true;

    private boolean saveImportsOnFileSystem =true;
    private String saveFileDirectory="/users/rob/devroot/engagementportal/externalfiles/uploads/";
    public boolean isAllowAllSender() {
        return allowAllSender;
    }

    public void setAllowAllSender(boolean allowAllSender) {
        this.allowAllSender = allowAllSender;
    }

    private boolean isValidSender(String emailAddress) {
        boolean isValidSender = false;
        if (this.validSenderList != null && emailAddress != null) {
            isValidSender = validSenderList.contains(emailAddress);
        }

        if (this.allowAllSender) {
            // used for testing
            isValidSender = true;
            logger.warn("allow all senders is set to true. this is a test setting");
        }
        return isValidSender;
    }

    public void process() {

        Session session = Session.getDefaultInstance(this.javaIMAPMailProperties);

        Store store = null;
        IMAPStore imapStore = null;
        Folder folderInbox = null;
        try {
            store = session.getStore("imaps");

            store.connect(this.javaIMAPMailProperties.getProperty("imaphost"),
                    Integer.parseInt(this.javaIMAPMailProperties.getProperty("mail.imap.port")),
                    this.javaIMAPMailProperties.getProperty("email"),
                    this.javaIMAPMailProperties.getProperty("password"));

            imapStore = (IMAPStore) store;

            folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);


            Message[] arrayMessages = folderInbox.getMessages();
            for (int i = 0; i < arrayMessages.length; i++) {

                Message message = arrayMessages[i];

                // make a disconnected copy
                // there is a bug that prevent pulling information
                // about the message while the message is connected to the
                // mail server.

                MimeMessage msgCopy = new MimeMessage((MimeMessage) message);
                Address[] fromAddress = message.getFrom();
                String sender = fromAddress[0].toString();

                // run some validations
                if (!this.isValidSender(sender)) {
                    continue;
                }

                if(logger.isDebugEnabled()) {
                    String fowardPrefix = "fw:";

                    logger.debug("subject: "+msgCopy.getSubject());
                    logger.debug("message id: "+msgCopy.getMessageID());
                    logger.debug("content language: "+msgCopy.getContentLanguage());

                    logger.debug("contenttype: "+msgCopy.getContentType());
                    logger.debug("description: "+msgCopy.getDescription());
                    logger.debug("encoding: "+msgCopy.getEncoding());
                    logger.debug("filename:"+msgCopy.getFileName());
                    logger.debug("receiveed date: "+msgCopy.getReceivedDate());
                    logger.debug("sent date: "+msgCopy.getSentDate());

                    Enumeration headers = msgCopy.getAllHeaderLines();
                    while(headers.hasMoreElements()){
                        Object obj=(String)headers.nextElement();
                        if(obj!=null){
                            String header = (String)obj;
                            logger.debug("HEADER:"+header);
                        }

                    }
                }
                Flags messageFlags = message.getFlags();
                String[] userFlags = messageFlags.getUserFlags();

                boolean skipMessage = false;

                if (userFlags != null) {
                    String wrkFlag = null;
                    for (int index = 0; index < userFlags.length; index++) {

                        wrkFlag = userFlags[index];
                        if (userFlags != null) {
                            if (MailConstants.PROCESSED_FLAG.contains(wrkFlag) || MailConstants.IGNORED_FLAG.contains(wrkFlag)) {
                                skipMessage = true;
                                logger.debug("message already processed - skipping");
                                continue;
                            } else if (MailConstants.ERROR_FLAG.contains(wrkFlag)) {
                                message.setFlags(MailConstants.ERROR_FLAG, false);
                                logger.debug("reset error flag");
                            }
                        }
                    }
                }
                if(skipMessage){
                    continue;
                }

                String contentType = null;
                try {
                    contentType = msgCopy.getContentType();
                } catch (NullPointerException e) {
                    logger.debug("this was happening becuase of an IMAP server bug", e);
                }

                // check for attachment potential
                if (!skipMessage && contentType != null && contentType.contains("multipart")) {
                    Multipart multiPart = (Multipart) msgCopy.getContent();
                    int numberOfParts = multiPart.getCount();
                    for (int partCount = 0; partCount < numberOfParts; partCount++) {

                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);

                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {

                            String fileName = part.getFileName();

                            if (fileName == null) {
                                logger.debug("filename is null");
                                continue;
                            }

                            String extension = fileName.substring(fileName.lastIndexOf("."));
                            if (extension == null || !extension.equals(supportedExtension)) {
                                logger.debug("extension not supported: " + extension);
                                continue;
                            }

                            // process the attachment
                            try {
                                if(saveImportsOnFileSystem) {
                                    logger.debug("in save file mode. saving import file to file system");
                                    File saveFile = new File(this.saveFileDirectory+Long.toString(new Date().getTime()));
                                    part.saveFile(saveFile);

                                }

                                this.importService.importOracleOBICSVSalesOrder(part.getInputStream(),ImportService.DEFAULT_MODEL_GROUPS,fileName);
                                message.setFlags(MailConstants.PROCESSED_FLAG, true);
                                logger.debug("set processed flag");
                            } catch (CSVConversionFailureException e) {
                                message.setFlags(MailConstants.ERROR_FLAG, true);
                                //message.setFlags(IMAPSOImportService.IGNORED_FLAG,true);
                                logger.debug("set error flag");
                                logger.error("Error processing so import file", e);
                            }
                        }
                    }

                } else {
                    message.setFlags(MailConstants.IGNORED_FLAG, true);
                    logger.debug("set ignored flag");
                }
            }//for loop


        } catch (NumberFormatException e) {
            logger.error(e);
        } catch (NoSuchProviderException e) {
            logger.error(e);
        } catch (MessagingException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        } finally {
            // disconnect
            try {
                folderInbox.close(false);
            } catch (MessagingException e) {
                // ignore
            }
            try {
                store.close();
            } catch (MessagingException e) {
                // ignore
            }
        }

    }
}

