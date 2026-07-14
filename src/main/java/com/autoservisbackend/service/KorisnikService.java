package com.autoservisbackend.service;

import com.autoservisbackend.dto.KorisnikDTO;
import com.autoservisbackend.entity.Korisnik;
import com.autoservisbackend.repository.KorisnikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KorisnikService {

    private final KorisnikRepository korisnikRepository;

    public List<Korisnik> getAll() {
        return korisnikRepository.findAll();
    }

    public Optional<Korisnik> getById(Long id) {
        return korisnikRepository.findById(id);
    }

    public Korisnik save(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    public void delete(Long id) {
        korisnikRepository.deleteById(id);
    }

    public KorisnikDTO toDTO(Korisnik korisnik) {
        KorisnikDTO dto = new KorisnikDTO();
        dto.setId(korisnik.getId());
        dto.setEmail(korisnik.getEmail());
        dto.setRola(korisnik.getRola());
        return dto;
    }

    public Korisnik fromDTO(KorisnikDTO dto) {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(dto.getId());
        korisnik.setEmail(dto.getEmail());
        korisnik.setRola(dto.getRola());
        return korisnik;
    }
}