package com.example.demo.config;

import com.example.demo.constants.ContentCreation;
import com.example.demo.constants.TextConstants;
import com.example.demo.dto.AccountCreationDTO;
import com.example.demo.dto.AccountDeletionDTO;
import com.example.demo.dto.OrderFinalisationDTO;
import com.example.demo.service.EmailSender;
import org.hibernate.query.Order;
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

    @RabbitListener(queues = "confirmDeleteClient")
    public void textFileConfirmReceiver(AccountDeletionDTO accountDeletionDTO)
    {
        try {
            emailSender.sendEmails(accountDeletionDTO.getEmail(), TextConstants.accountDeletionSubject, ContentCreation.accountDeletionContent(accountDeletionDTO));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "confirmOrderFinalisation")
    public void orderFinalisationReceiver(OrderFinalisationDTO orderFinalisationDTO)
    {
        try {
            emailSender.sendAttachmentsEmails(orderFinalisationDTO.getEmail(), TextConstants.orderFinalisationSubject, ContentCreation.orderFinalisationContent(orderFinalisationDTO));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
