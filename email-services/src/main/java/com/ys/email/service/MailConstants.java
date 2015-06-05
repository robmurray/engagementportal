package com.ys.email.service;

import javax.mail.Flags;

/**
 * Created by rob on 6/3/15.
 */
public interface MailConstants {
    public static final Flags PROCESSED_FLAG = new Flags("PROCESSED");
    public static final Flags ERROR_FLAG = new Flags("ERROR");
    public static final Flags IGNORED_FLAG = new Flags("IGNORED");
}
