package com.example.demo.validators;

import com.example.demo.dto.AccountCreationDTO;
import com.example.demo.dto.AccountDeletionDTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountValidator {

    public static boolean validateClient(AccountCreationDTO accountCreationDTO)
    {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z]+(.ro|.com|.org)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(accountCreationDTO.getEmail());

        Pattern pattern2 = Pattern.compile("[a-zA-Z -]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(accountCreationDTO.getNume());
        Matcher matcher3 = pattern2.matcher(accountCreationDTO.getPrenume());

        return (matcher.matches() && matcher2.matches() && matcher3.matches() && !accountCreationDTO.getUuid().toString().equals(""));
    }

    public static boolean validateClient(AccountDeletionDTO accountDeletionDTO)
    {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z]+(.ro|.com|.org)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(accountDeletionDTO.getEmail());

        Pattern pattern2 = Pattern.compile("[a-zA-Z -]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(accountDeletionDTO.getNume());
        Matcher matcher3 = pattern2.matcher(accountDeletionDTO.getPrenume());

        return (matcher.matches() && matcher2.matches() && matcher3.matches() && !accountDeletionDTO.getUuid().toString().equals(""));
    }

}
