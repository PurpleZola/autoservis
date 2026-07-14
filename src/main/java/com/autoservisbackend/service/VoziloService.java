package com.autoservisbackend.service;

import com.autoservisbackend.dto.VoziloDTO;
import com.autoservisbackend.entity.Klijent;
import com.autoservisbackend.entity.Vozilo;
import com.autoservisbackend.repository.VoziloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoziloService {

    private final VoziloRepository voziloRepository;

    public List<Vozilo> getAll() {
        return voziloRepository.findAll();
    }

    public Optional<Vozilo> getById(Long id) {
        return voziloRepository.findById(id);
    }

    public Vozilo save(Vozilo vozilo) {
        return voziloRepository.save(vozilo);
    }

    public void delete(Long id) {
        voziloRepository.deleteById(id);
    }

    public VoziloDTO toDTO(Vozilo vozilo) {
        VoziloDTO dto = new VoziloDTO();
        dto.setId(vozilo.getId());
        dto.setMarka(vozilo.getMarka());
        dto.setModel(vozilo.getModel());
        dto.setGodina(vozilo.getGodina());
        dto.setRegistracija(vozilo.getRegistracija());
        dto.setBoja(vozilo.getBoja());
        dto.setBrojSasije(vozilo.getBrojSasije());
        dto.setGorivo(vozilo.getGorivo());
        dto.setKilometraza(vozilo.getKilometraza());
        if (vozilo.getKlijent() != null) {
            dto.setKlijentId(vozilo.getKlijent().getId());
        }
        return dto;
    }

    public Vozilo fromDTO(VoziloDTO dto) {
        Vozilo vozilo = new Vozilo();
        vozilo.setId(dto.getId());
        vozilo.setMarka(dto.getMarka());
        vozilo.setModel(dto.getModel());
        vozilo.setGodina(dto.getGodina());
        vozilo.setRegistracija(dto.getRegistracija());
        vozilo.setBoja(dto.getBoja());
        vozilo.setBrojSasije(dto.getBrojSasije());
        vozilo.setGorivo(dto.getGorivo());
        vozilo.setKilometraza(dto.getKilometraza());
        if (dto.getKlijentId() != null) {
            Klijent klijent = new Klijent();
            klijent.setId(dto.getKlijentId());
            vozilo.setKlijent(klijent);
        }
        return vozilo;
    }
}