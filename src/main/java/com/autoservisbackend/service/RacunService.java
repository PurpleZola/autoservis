package com.autoservisbackend.service;

import com.autoservisbackend.dto.RacunDTO;
import com.autoservisbackend.entity.Racun;
import com.autoservisbackend.entity.ServisniNalog;
import com.autoservisbackend.repository.RacunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RacunService {

    private final RacunRepository racunRepository;

    public List<Racun> getAll() {
        return racunRepository.findAll();
    }

    public Optional<Racun> getById(Long id) {
        return racunRepository.findById(id);
    }

    public Racun save(Racun racun) {
        return racunRepository.save(racun);
    }

    public void delete(Long id) {
        racunRepository.deleteById(id);
    }

    public RacunDTO toDTO(Racun racun) {
        RacunDTO dto = new RacunDTO();
        dto.setId(racun.getId());
        dto.setDatum(racun.getDatum());
        dto.setUkupnaCijena(racun.getUkupnaCijena());
        dto.setNapomena(racun.getNapomena());
        if (racun.getServisniNalog() != null) {
            dto.setServisniNalogId(racun.getServisniNalog().getId());
        }
        return dto;
    }

    public Racun fromDTO(RacunDTO dto) {
        Racun racun = new Racun();
        racun.setId(dto.getId());
        racun.setDatum(dto.getDatum());
        racun.setUkupnaCijena(dto.getUkupnaCijena());
        racun.setNapomena(dto.getNapomena());
        if (dto.getServisniNalogId() != null) {
            ServisniNalog servisniNalog = new ServisniNalog();
            servisniNalog.setId(dto.getServisniNalogId());
            racun.setServisniNalog(servisniNalog);
        }
        return racun;
    }
}