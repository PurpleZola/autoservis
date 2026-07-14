package com.autoservisbackend.controller;

import com.autoservisbackend.dto.ServisniNalogDTO;
import com.autoservisbackend.entity.ServisniNalog;
import com.autoservisbackend.service.ServisniNalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/servisni-nalozi")
@RequiredArgsConstructor
public class ServisniNalogController {

    private final ServisniNalogService servisniNalogService;

    @GetMapping
    public ResponseEntity<List<ServisniNalogDTO>> getAll() {
        List<ServisniNalogDTO> result = servisniNalogService.getAll().stream()
                .map(servisniNalogService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServisniNalogDTO> getById(@PathVariable Long id) {
        return servisniNalogService.getById(id)
                .map(servisniNalogService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServisniNalogDTO> create(@RequestBody ServisniNalogDTO dto) {
        ServisniNalog saved = servisniNalogService.save(servisniNalogService.fromDTO(dto));
        return ResponseEntity.status(201).body(servisniNalogService.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServisniNalogDTO> update(@PathVariable Long id, @RequestBody ServisniNalogDTO dto) {
        if (servisniNalogService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ServisniNalog servisniNalog = servisniNalogService.fromDTO(dto);
        servisniNalog.setId(id);
        return ResponseEntity.ok(servisniNalogService.toDTO(servisniNalogService.save(servisniNalog)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (servisniNalogService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        servisniNalogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
