package com.autoservisbackend.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RacunDTO {
    private Long id;
    private LocalDate datum;
    private Double ukupnaCijena;
    private String napomena;
    private Long servisniNalogId;
}