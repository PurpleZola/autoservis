package com.autoservisbackend.dto;

import lombok.Data;

@Data
public class KlijentDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String telefon;
    private String adresa;
    private Long korisnikId;
}