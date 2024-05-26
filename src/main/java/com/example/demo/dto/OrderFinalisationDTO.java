package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFinalisationDTO {

    private UUID uuid;
    private String dataComanda;
    private String nume;
    private String prenume;
    private String email;
}