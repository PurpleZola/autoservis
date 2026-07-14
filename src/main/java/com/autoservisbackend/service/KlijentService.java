package com.autoservisbackend.service;

import com.autoservisbackend.dto.KlijentDTO;
import com.autoservisbackend.entity.Klijent;
import com.autoservisbackend.entity.Korisnik;
import com.autoservisbackend.repository.KlijentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KlijentService {

    private final KlijentRepository klijentRepository;

    public List<Klijent> getAll() {
        return klijentRepository.findAll();
    }

    public Optional<Klijent> getById(Long id) {
        return klijentRepository.findById(id);
    }

    public Klijent save(Klijent klijent) {
        return klijentRepository.save(klijent);
    }

    public void delete(Long id) {
        klijentRepository.deleteById(id);
    }

    public KlijentDTO toDTO(Klijent klijent) {
        KlijentDTO dto = new KlijentDTO();
        dto.setId(klijent.getId());
        dto.setIme(klijent.getIme());
        dto.setPrezime(klijent.getPrezime());
        dto.setTelefon(klijent.getTelefon());
        dto.setAdresa(klijent.getAdresa());
        if (klijent.getKorisnik() != null) {
            dto.setKorisnikId(klijent.getKorisnik().getId());
        }
        return dto;
    }

    public Klijent fromDTO(KlijentDTO dto) {
        Klijent klijent = new Klijent();
        klijent.setId(dto.getId());
        klijent.setIme(dto.getIme());
        klijent.setPrezime(dto.getPrezime());
        klijent.setTelefon(dto.getTelefon());
        klijent.setAdresa(dto.getAdresa());
        if (dto.getKorisnikId() != null) {
            Korisnik korisnik = new Korisnik();
            korisnik.setId(dto.getKorisnikId());
            klijent.setKorisnik(korisnik);
        }
        return klijent;
    }
}