package com.autoservisbackend.service;

import com.autoservisbackend.dto.DioDTO;
import com.autoservisbackend.entity.Dio;
import com.autoservisbackend.entity.ServisniNalog;
import com.autoservisbackend.repository.DioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DioService {

    private final DioRepository dioRepository;

    public List<Dio> getAll() {
        return dioRepository.findAll();
    }

    public Optional<Dio> getById(Long id) {
        return dioRepository.findById(id);
    }

    public Dio save(Dio dio) {
        return dioRepository.save(dio);
    }

    public void delete(Long id) {
        dioRepository.deleteById(id);
    }

    public DioDTO toDTO(Dio dio) {
        DioDTO dto = new DioDTO();
        dto.setId(dio.getId());
        dto.setNaziv(dio.getNaziv());
        dto.setCijena(dio.getCijena());
        dto.setKolicinaNaStanju(dio.getKolicinaNaStanju());
        dto.setMinimalnaKolicina(dio.getMinimalnaKolicina());
        if (dio.getServisniNalog() != null) {
            dto.setServisniNalogId(dio.getServisniNalog().getId());
        }
        return dto;
    }

    public Dio fromDTO(DioDTO dto) {
        Dio dio = new Dio();
        dio.setId(dto.getId());
        dio.setNaziv(dto.getNaziv());
        dio.setCijena(dto.getCijena());
        dio.setKolicinaNaStanju(dto.getKolicinaNaStanju());
        dio.setMinimalnaKolicina(dto.getMinimalnaKolicina());
        if (dto.getServisniNalogId() != null) {
            ServisniNalog servisniNalog = new ServisniNalog();
            servisniNalog.setId(dto.getServisniNalogId());
            dio.setServisniNalog(servisniNalog);
        }
        return dio;
    }
}