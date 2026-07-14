package com.autoservisbackend.dto;

import lombok.Data;

@Data
public class KvarDTO {
    private Long id;
    private String naziv;
    private String opis;
    private Long servisniNalogId;
}