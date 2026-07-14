package com.autoservisbackend.service;

import com.autoservisbackend.dto.ServisniNalogDTO;
import com.autoservisbackend.entity.ServisniNalog;
import com.autoservisbackend.entity.Serviser;
import com.autoservisbackend.entity.Vozilo;
import com.autoservisbackend.repository.ServisniNalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServisniNalogService {

    private final ServisniNalogRepository servisniNalogRepository;

    public List<ServisniNalog> getAll() {
        return servisniNalogRepository.findAll();
    }

    public Optional<ServisniNalog> getById(Long id) {
        return servisniNalogRepository.findById(id);
    }

    public ServisniNalog save(ServisniNalog servisniNalog) {
        return servisniNalogRepository.save(servisniNalog);
    }

    public void delete(Long id) {
        servisniNalogRepository.deleteById(id);
    }

    public ServisniNalogDTO toDTO(ServisniNalog servisniNalog) {
        ServisniNalogDTO dto = new ServisniNalogDTO();
        dto.setId(servisniNalog.getId());
        dto.setDatumPrijema(servisniNalog.getDatumPrijema());
        dto.setDatumZavrsetka(servisniNalog.getDatumZavrsetka());
        dto.setOpisProblema(servisniNalog.getOpisProblema());
        dto.setStatus(servisniNalog.getStatus());
        dto.setSledeciServisDatum(servisniNalog.getSledeciServisDatum());
        if (servisniNalog.getVozilo() != null) {
            dto.setVoziloId(servisniNalog.getVozilo().getId());
        }
        if (servisniNalog.getServiser() != null) {
            dto.setServiserID(servisniNalog.getServiser().getId());
        }
        return dto;
    }

    public ServisniNalog fromDTO(ServisniNalogDTO dto) {
        ServisniNalog servisniNalog = new ServisniNalog();
        servisniNalog.setId(dto.getId());
        servisniNalog.setDatumPrijema(dto.getDatumPrijema());
        servisniNalog.setDatumZavrsetka(dto.getDatumZavrsetka());
        servisniNalog.setOpisProblema(dto.getOpisProblema());
        servisniNalog.setStatus(dto.getStatus());
        servisniNalog.setSledeciServisDatum(dto.getSledeciServisDatum());
        if (dto.getVoziloId() != null) {
            Vozilo vozilo = new Vozilo();
            vozilo.setId(dto.getVoziloId());
            servisniNalog.setVozilo(vozilo);
        }
        if (dto.getServiserID() != null) {
            Serviser serviser = new Serviser();
            serviser.setId(dto.getServiserID());
            servisniNalog.setServiser(serviser);
        }
        return servisniNalog;
    }
}