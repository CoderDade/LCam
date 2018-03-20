package com.dade.lcam.dailymail.mail;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class MailServices {

    public void sendHtmlMail(String html) throws MessagingException {
        if (StringUtils.isBlank(html)){
            return;
        }
        Properties p = System.getProperties();
        Session session = Session.getInstance(p, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(p.getProperty("mail.smtp.user"),p.getProperty("mail.smtp.pass"));
            }
        });
        Message message = new MimeMessage(session);

        Multipart mainPart = new MimeMultipart();
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(html, "text/html; charset=utf-8");
        mainPart.addBodyPart(bodyPart);
        message.setContent(mainPart);
        Transport.send(message);


    }

    public void sendTextMail(String text){

    }


    private Message getMessage(){
        return null;
    }

}
