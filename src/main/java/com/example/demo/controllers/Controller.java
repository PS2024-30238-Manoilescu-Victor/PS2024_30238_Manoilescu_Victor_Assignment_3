package com.example.demo.controllers;

import com.example.demo.constants.ContentCreation;
import com.example.demo.constants.TextConstants;
import com.example.demo.dto.AccountCreationDTO;
import com.example.demo.dto.AccountDeletionDTO;
import com.example.demo.dto.OrderFinalisationDTO;
import com.example.demo.service.EmailSender;
import com.example.demo.validators.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private final EmailSender emailSender;

    public Controller(EmailSender emailSender){
        this.emailSender = emailSender;
    }

    @PostMapping("/accountCreatedEmail")
    private ResponseEntity<String> accountCreated(@RequestHeader HttpHeaders header, @RequestBody AccountCreationDTO accountCreationDTO){
        if(AccountValidator.validateClient(accountCreationDTO))
        {
            String token = header.getFirst("AUTHORIZATION");
            if(token != null && token.startsWith("Bearer"))
            {
                token = token.substring(7);
                if(token.startsWith(accountCreationDTO.getUuid().toString()) && token.substring(37).contains(TextConstants.TOKEN))
                {
                    emailSender.sendEmails(accountCreationDTO.getEmail(),TextConstants.accountCreationSubject, ContentCreation.accountCreationContent(accountCreationDTO));

                    return  new ResponseEntity<>(TextConstants.emailSent, HttpStatus.ACCEPTED);
                }
                return new ResponseEntity<>(TextConstants.invalidToken, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(TextConstants.invalidToken, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(TextConstants.invalidData, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/accountDeletedEmail")
    private ResponseEntity<String> txtFileGenerated(@RequestHeader HttpHeaders header, @RequestBody AccountDeletionDTO accountDeletionDTO){
        if(AccountValidator.validateClient(accountDeletionDTO))
        {
            String token = header.getFirst("AUTHORIZATION");
            if(token != null && token.startsWith("Bearer"))
            {
                token = token.substring(7);
                if(token.startsWith(accountDeletionDTO.getUuid().toString()) && token.substring(37).contains(TextConstants.TOKEN))
                {
                    emailSender.sendEmails(accountDeletionDTO.getEmail(),TextConstants.accountDeletionSubject, ContentCreation.accountDeletionContent(accountDeletionDTO));

                    return  new ResponseEntity<>(TextConstants.emailSent, HttpStatus.ACCEPTED);
                }
                return new ResponseEntity<>(TextConstants.invalidToken, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(TextConstants.invalidToken, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(TextConstants.invalidData, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/orderFinaliseddEmail")
    private ResponseEntity<String> orderFinalised(@RequestHeader HttpHeaders header, @RequestBody OrderFinalisationDTO orderFinalisationDTO){
        if(AccountValidator.validateClient(orderFinalisationDTO))
        {
            String token = header.getFirst("AUTHORIZATION");
            if(token != null && token.startsWith("Bearer"))
            {
                token = token.substring(7);
                if(token.startsWith(orderFinalisationDTO.getUuid().toString()) && token.substring(37).contains(TextConstants.TOKEN))
                {
                    emailSender.sendAttachmentsEmails(orderFinalisationDTO.getEmail(),TextConstants.accountDeletionSubject, ContentCreation.orderFinalisationContent(orderFinalisationDTO));

                    return  new ResponseEntity<>(TextConstants.emailSent, HttpStatus.ACCEPTED);
                }
                return new ResponseEntity<>(TextConstants.invalidToken, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(TextConstants.invalidToken, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(TextConstants.invalidData, HttpStatus.BAD_REQUEST);
    }

}
