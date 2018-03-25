package com.dade.lcam.dailymail.mail;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

@Service
@PropertySource("classpath:person.properties")
public class MailServices {

    private String host = "smtp.163.com"; //163的服务器
    @Value("${person.account}")
    private String formName;//你的邮箱

    @Value("${person.pwd}")
    private String password; //授权码

    @Value("${person.replayAddress}")
    private String replayAddress; //你的邮箱

    public void sendHtmlMail(String html) throws MessagingException, UnsupportedEncodingException {
        if (StringUtils.isBlank(html)){
            return;
        }
        Message message = getMessage();

        Multipart mainPart = new MimeMultipart();
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(html, "text/html; charset=utf-8");
        mainPart.addBodyPart(bodyPart);
        message.setContent(mainPart);
        Transport.send(message);


    }

    // todo send TextMail
    public void sendTextMail(String text){
    }


    private Message getMessage() throws UnsupportedEncodingException, MessagingException {

        final Properties p = System.getProperties() ;
        p.setProperty("mail.smtp.host", host);
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.user", formName);
        p.setProperty("mail.smtp.pass", password);


        Session session = Session.getInstance(p, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(p.getProperty("mail.smtp.user"),p.getProperty("mail.smtp.pass"));
            }
        });
        session.setDebug(true);

        Message message = new MimeMessage(session);
        //消息发送的主题
        message.setSubject("Hello Joe Chang");
        //接受消息的人
        message.setReplyTo(InternetAddress.parse(replayAddress));
        //消息的发送者
        message.setFrom(new InternetAddress(p.getProperty("mail.smtp.user"),"JoeChang"));
        // 创建邮件的接收者地址，并设置到邮件消息中
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(replayAddress));
        // 消息发送的时间
        message.setSentDate(new Date());

        return message;
    }

}
