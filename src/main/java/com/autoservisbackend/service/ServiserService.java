package com.autoservisbackend.service;

import com.autoservisbackend.dto.ServiserDTO;
import com.autoservisbackend.entity.Serviser;
import com.autoservisbackend.repository.ServiserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiserService {

    private final ServiserRepository serviserRepository;

    public List<Serviser> getAll() {
        return serviserRepository.findAll();
    }

    public Optional<Serviser> getById(Long id) {
        return serviserRepository.findById(id);
    }

    public Serviser save(Serviser serviser) {
        return serviserRepository.save(serviser);
    }

    public void delete(Long id) {
        serviserRepository.deleteById(id);
    }

    public ServiserDTO toDTO(Serviser serviser) {
        ServiserDTO dto = new ServiserDTO();
        dto.setId(serviser.getId());
        dto.setIme(serviser.getIme());
        dto.setPrezime(serviser.getPrezime());
        dto.setSpecijalnost(serviser.getSpecijalnost());
        dto.setTelefon(serviser.getTelefon());
        return dto;
    }

    public Serviser fromDTO(ServiserDTO dto) {
        Serviser serviser = new Serviser();
        serviser.setId(dto.getId());
        serviser.setIme(dto.getIme());
        serviser.setPrezime(dto.getPrezime());
        serviser.setSpecijalnost(dto.getSpecijalnost());
        serviser.setTelefon(dto.getTelefon());
        return serviser;
    }
}