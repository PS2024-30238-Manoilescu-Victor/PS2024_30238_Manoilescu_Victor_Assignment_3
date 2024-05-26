package com.example.demo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

    public void sendAttachmentsEmails(String receiver, String subject, String content)
    {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            mimeMessage.setFrom(new InternetAddress("victormanoilescu@gmail.com"));
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiver));
            mimeMessage.setSubject(subject);
            //mimeMessage.setContent(content, "text/html; charset=utf-8");
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart bodyPartTxt = new MimeBodyPart();
            bodyPartTxt.setContent(content, "text/html; charset=utf-8");
            MimeBodyPart bodyPartFile = new MimeBodyPart();
            bodyPartFile.attachFile("C:/Users/Victor/Downloads/OrderInfo.txt");

            multipart.addBodyPart(bodyPartTxt);
            multipart.addBodyPart(bodyPartFile);

            mimeMessage.setContent(multipart);
            mailSender.send(mimeMessage);
        }
        catch (MessagingException exception){

        }
        catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

}
