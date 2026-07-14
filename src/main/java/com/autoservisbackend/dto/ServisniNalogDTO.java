package com.autoservisbackend.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ServisniNalogDTO {
    private Long id;
    private LocalDate datumPrijema;
    private LocalDate datumZavrsetka;
    private String opisProblema;
    private String status;
    private LocalDate sledeciServisDatum;
    private Long voziloId;
    private Long serviserID;
}