package com.example.demo.config;

import com.example.demo.constants.ContentCreation;
import com.example.demo.constants.TextConstants;
import com.example.demo.dto.AccountCreationDTO;
import com.example.demo.service.EmailSender;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @Autowired
    private EmailSender emailSender;

    @RabbitListener(queues = "confirmAccountCreation")
    public void accountCreationReceiver(AccountCreationDTO accountCreationDTO)
    {
        try {
            emailSender.sendEmails(accountCreationDTO.getEmail(), TextConstants.accountCreationSubject, ContentCreation.accountCreationContent(accountCreationDTO));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
