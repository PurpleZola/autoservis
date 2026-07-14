package com.autoservisbackend.dto;

import lombok.Data;

@Data
public class VoziloDTO {
    private Long id;
    private String marka;
    private String model;
    private int godina;
    private String registracija;
    private String boja;
    private String brojSasije;
    private String gorivo;
    private int kilometraza;
    private Long klijentId;
}