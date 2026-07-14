package com.autoservisbackend.dto;

import lombok.Data;

@Data
public class DioDTO {
    private Long id;
    private String naziv;
    private Double cijena;
    private int kolicinaNaStanju;
    private int minimalnaKolicina;
    private Long servisniNalogId;
}
