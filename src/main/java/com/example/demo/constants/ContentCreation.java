package com.example.demo.constants;

import com.example.demo.dto.AccountCreationDTO;

import java.io.BufferedReader;
import java.io.FileReader;

public class ContentCreation {

    public static String accountCreationContent(AccountCreationDTO accountCreationDTO)
    {
        StringBuilder html = new StringBuilder();

        try {
            FileReader fr = new FileReader("src/main/resources/templates/AccountCreation.html");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                html.append(line);
            }

            br.close();
        } catch (Exception e) {
            html.append("Your account was succesfully created");
        }

        String emailBody = html.toString();
        emailBody = emailBody.replace("${nume}", accountCreationDTO.getNume());
        emailBody = emailBody.replace("${prenume}", accountCreationDTO.getPrenume());

        return emailBody;
    }

}
