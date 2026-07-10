package com.autoservisbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "klijent")
public class Klijent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    private String telefon;

    private String adresa;

    @OneToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;
}