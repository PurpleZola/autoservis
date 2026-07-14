package com.autoservisbackend.dto;

import lombok.Data;

@Data
public class UslugaDTO {
    private Long id;
    private String naziv;
    private Double cijena;
    private Long servisniNalogId;
}
