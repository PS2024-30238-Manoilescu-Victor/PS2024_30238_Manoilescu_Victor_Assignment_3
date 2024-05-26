package com.example.demo.constants;

import com.example.demo.dto.AccountCreationDTO;
import com.example.demo.dto.AccountDeletionDTO;
import com.example.demo.dto.OrderFinalisationDTO;

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

    public static String accountDeletionContent(AccountDeletionDTO accountDeletionDTO)
    {
        StringBuilder html = new StringBuilder();

        try {
            FileReader fr = new FileReader("src/main/resources/templates/AccountDeletion.html");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                html.append(line);
            }

            br.close();
        } catch (Exception e) {
            html.append("Account succesfully deleted");
        }

        String emailBody = html.toString();
        emailBody = emailBody.replace("${nume}", accountDeletionDTO.getNume());
        emailBody = emailBody.replace("${prenume}", accountDeletionDTO.getPrenume());

        return emailBody;
    }

    public static String orderFinalisationContent(OrderFinalisationDTO orderFinalisationDTO)
    {
        StringBuilder html = new StringBuilder();

        try {
            FileReader fr = new FileReader("src/main/resources/templates/OrderFinalisation.html");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                html.append(line);
            }

            br.close();
        } catch (Exception e) {
            html.append("Order succesfully finalised");
        }

        String emailBody = html.toString();
        emailBody = emailBody.replace("${nume}", orderFinalisationDTO.getNume());
        emailBody = emailBody.replace("${uuid}", orderFinalisationDTO.getUuid().toString());

        return emailBody;
    }

}
