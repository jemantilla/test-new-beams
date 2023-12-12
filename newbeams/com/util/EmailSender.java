package com.util;

import java.util.Properties;
import java.util.StringTokenizer;
import javax.mail.*;
import javax.mail.event.*;
import javax.mail.internet.*;

public class EmailSender extends GenericService {
    static Properties pro = new Properties();
    
    static {
        pro.put("mail.smtp.host",Configuration.SMTPHOST);   // like "wecorp.com"
        pro.put("mail.from",Configuration.MAIL_FROM);      // like rajivs@wecorp.com
    }

    synchronized public void sendEmail(String[] pAddresses, String pMessageBody){
        try{
            Session session=Session.getInstance(pro,null);
            Message message = new MimeMessage(session);
            InternetAddress[] address = new InternetAddress[pAddresses.length];
            for (int i=0; i<pAddresses.length; i++) {
                address[i] = new InternetAddress(pAddresses[i]);
            }
            message.setRecipients(Message.RecipientType.TO, address);
            message.setFrom(new InternetAddress(Configuration.MAIL_FROM));
            message.setSubject(Configuration.SUBJECT_STR);
            message.setContent(pMessageBody ,"text/plain");
            Transport.send(message);
        }catch(Exception e){
            System.out.print("Invalid Email");
            e.printStackTrace();
            //ErrorStr += "Please Enter Valid EmailAddress";
        }
    }
    // validate Email Address
    synchronized public boolean validateEmailAdddress(String pEmail){
        if (pEmail != null) {
            if (pEmail.startsWith(".") || pEmail.endsWith(".") || (pEmail.indexOf("@") == -1)) {
                return false;
            }else {
                for (int i = 0; i < pEmail.length(); i++) {
                    char letter = pEmail.charAt(i);
                    if ((letter == '"') || (letter == ';') || (letter == ':') || (letter == '<')
                        || (letter == '>') || (letter == '(') || (letter == ')') || (letter == '*')
                        || (letter == ' ') || (letter == ',') ){
                        return false;
                    }
                }
            }
            StringTokenizer st = new StringTokenizer(pEmail, "@");
            if (st.countTokens() != 2) {
                return false;
            }
            String token1 = st.nextToken();
            String token2 = st.nextToken();
            if (token2.indexOf(".") == -1) {
                return false;
            }
            return true;
        }else {
            return false;
        }
    }
}
