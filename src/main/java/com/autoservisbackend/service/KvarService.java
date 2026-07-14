package com.autoservisbackend.service;

import com.autoservisbackend.dto.KvarDTO;
import com.autoservisbackend.entity.Kvar;
import com.autoservisbackend.entity.ServisniNalog;
import com.autoservisbackend.repository.KvarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KvarService {

    private final KvarRepository kvarRepository;

    public List<Kvar> getAll() {
        return kvarRepository.findAll();
    }

    public Optional<Kvar> getById(Long id) {
        return kvarRepository.findById(id);
    }

    public Kvar save(Kvar kvar) {
        return kvarRepository.save(kvar);
    }

    public void delete(Long id) {
        kvarRepository.deleteById(id);
    }

    public KvarDTO toDTO(Kvar kvar) {
        KvarDTO dto = new KvarDTO();
        dto.setId(kvar.getId());
        dto.setNaziv(kvar.getNaziv());
        dto.setOpis(kvar.getOpis());
        if (kvar.getServisniNalog() != null) {
            dto.setServisniNalogId(kvar.getServisniNalog().getId());
        }
        return dto;
    }

    public Kvar fromDTO(KvarDTO dto) {
        Kvar kvar = new Kvar();
        kvar.setId(dto.getId());
        kvar.setNaziv(dto.getNaziv());
        kvar.setOpis(dto.getOpis());
        if (dto.getServisniNalogId() != null) {
            ServisniNalog servisniNalog = new ServisniNalog();
            servisniNalog.setId(dto.getServisniNalogId());
            kvar.setServisniNalog(servisniNalog);
        }
        return kvar;
    }
}