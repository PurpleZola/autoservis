package com.autoservisbackend.controller;

import com.autoservisbackend.dto.UslugaDTO;
import com.autoservisbackend.entity.Usluga;
import com.autoservisbackend.service.UslugaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usluge")
@RequiredArgsConstructor
public class UslugaController {

    private final UslugaService uslugaService;

    @GetMapping
    public ResponseEntity<List<UslugaDTO>> getAll() {
        List<UslugaDTO> result = uslugaService.getAll().stream()
                .map(uslugaService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UslugaDTO> getById(@PathVariable Long id) {
        return uslugaService.getById(id)
                .map(uslugaService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UslugaDTO> create(@RequestBody UslugaDTO dto) {
        Usluga saved = uslugaService.save(uslugaService.fromDTO(dto));
        return ResponseEntity.status(201).body(uslugaService.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UslugaDTO> update(@PathVariable Long id, @RequestBody UslugaDTO dto) {
        if (uslugaService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usluga usluga = uslugaService.fromDTO(dto);
        usluga.setId(id);
        return ResponseEntity.ok(uslugaService.toDTO(uslugaService.save(usluga)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (uslugaService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        uslugaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
