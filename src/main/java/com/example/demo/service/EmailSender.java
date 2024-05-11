package com.example.demo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmails(String receiver, String subject, String content)
    {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            mimeMessage.setFrom(new InternetAddress("victormanoilescu@gmail.com"));
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiver));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(content, "text/html; charset=utf-8");
            mailSender.send(mimeMessage);
        }
        catch (MessagingException exception){

        }
    }

}
