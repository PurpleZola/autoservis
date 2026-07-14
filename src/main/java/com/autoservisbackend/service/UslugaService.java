package com.autoservisbackend.service;

import com.autoservisbackend.dto.UslugaDTO;
import com.autoservisbackend.entity.ServisniNalog;
import com.autoservisbackend.entity.Usluga;
import com.autoservisbackend.repository.UslugaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UslugaService {

    private final UslugaRepository uslugaRepository;

    public List<Usluga> getAll() {
        return uslugaRepository.findAll();
    }

    public Optional<Usluga> getById(Long id) {
        return uslugaRepository.findById(id);
    }

    public Usluga save(Usluga usluga) {
        return uslugaRepository.save(usluga);
    }

    public void delete(Long id) {
        uslugaRepository.deleteById(id);
    }

    public UslugaDTO toDTO(Usluga usluga) {
        UslugaDTO dto = new UslugaDTO();
        dto.setId(usluga.getId());
        dto.setNaziv(usluga.getNaziv());
        dto.setCijena(usluga.getCijena());
        if (usluga.getServisniNalog() != null) {
            dto.setServisniNalogId(usluga.getServisniNalog().getId());
        }
        return dto;
    }

    public Usluga fromDTO(UslugaDTO dto) {
        Usluga usluga = new Usluga();
        usluga.setId(dto.getId());
        usluga.setNaziv(dto.getNaziv());
        usluga.setCijena(dto.getCijena());
        if (dto.getServisniNalogId() != null) {
            ServisniNalog servisniNalog = new ServisniNalog();
            servisniNalog.setId(dto.getServisniNalogId());
            usluga.setServisniNalog(servisniNalog);
        }
        return usluga;
    }
}