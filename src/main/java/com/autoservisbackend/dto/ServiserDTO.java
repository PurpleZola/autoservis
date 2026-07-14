package com.autoservisbackend.dto;

import lombok.Data;

@Data
public class ServiserDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String specijalnost;
    private String telefon;
}