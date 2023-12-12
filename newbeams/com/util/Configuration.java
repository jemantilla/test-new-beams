package com.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Properties;

public class Configuration {
    private static ResourceBundle MsgResources = ResourceBundle.getBundle("config");
    public static final String DBURL = MsgResources.getString("DBURL");
    public static final String DRIVER = MsgResources.getString("DRIVER");
    public static final String USER_NAME = MsgResources.getString("USER_NAME");
    public static final String PASSWORD = MsgResources.getString("PASSWORD");
    public static final String SIGN_UP_SQL = MsgResources.getString("SIGN_UP_SQL");
    public static final String SMTPHOST = MsgResources.getString("SMTPHOST");
    public static final String MAIL_FROM = MsgResources.getString("MAIL_FROM");
    public static final String SUBJECT_STR = MsgResources.getString("SUBJECT_STR");
    public static final String LOG_IN_SQL = MsgResources.getString("LOG_IN_SQL");
    public static final String FORGOT_PASSWORD_SQL = MsgResources.getString("FORGOT_PASSWORD_SQL");
}
