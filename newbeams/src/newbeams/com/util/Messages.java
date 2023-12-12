package com.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Properties;

public class Messages {
    private static ResourceBundle MsgResources = ResourceBundle.getBundle("Msgs");
    public static final String TEST_INVALID_PASSWORD = MsgResources.getString("TEST_INVALID_PASSWORD");
    public static final String INVALID_USERNAME = MsgResources.getString("INVALID_USERNAME");
    public static final String INVALID_PASSWORD = MsgResources.getString("INVALID_PASSWORD");
    public static final String INVALID_NEWPASSWORD = MsgResources.getString("INVALID_NEWPASSWORD");
    public static final String INVALID_OLDPASSWORD = MsgResources.getString("INVALID_OLDPASSWORD");
    public static final String INVALID_REENTER_PASSWORD = MsgResources.getString("INVALID_REENTER_PASSWORD");
    public static final String BOTH_PASSWORD_MISMATCH = MsgResources.getString("BOTH_PASSWORD_MISMATCH");
    public static final String BOTH_NEWPASSWORD_MISMATCH = MsgResources.getString("BOTH_NEWPASSWORD_MISMATCH");
    public static final String INVALID_FIRSTNAME = MsgResources.getString("INVALID_FIRSTNAME");
    public static final String INVALID_LASTNAME = MsgResources.getString("INVALID_LASTNAME");
    public static final String INVALID_EMAIL = MsgResources.getString("INVALID_EMAIL");
    public static final String UNSELECTED_REDIO = MsgResources.getString("UNSELECTED_REDIO");
    public static final String INVALID_NUMBER = MsgResources.getString("INVALID_NUMBER");
    public static final String HIGH_LOAD = MsgResources.getString("HIGH_LOAD");
    public static final String INVALID_LOAD_DECIMAL = MsgResources.getString("INVALID_LOAD_DECIMAL");
    public static final String INVALID_PROJECTNAME = MsgResources.getString("INVALID_PROJECTNAME");
    public static final String PROJECTNAME_EXIST = MsgResources.getString("PROJECTNAME_EXIST");
    public static final String INVALID_AGREE = MsgResources.getString("INVALID_AGREE");

    public static String getMsgStr(String pMsg, Object[] pArguments){
        return MessageFormat.format(pMsg, pArguments);
    }
}

/*  Copy this code in class from where you want to call
  Object [] userName = new Object[1];
        userName[0] = "Fanlink Inc";
        System.out.println(Messages.getMsgStr(Messages.INVALID_USERNAME , userName));
*/        
