package com.autoservisbackend.controller;

import com.autoservisbackend.dto.VoziloDTO;
import com.autoservisbackend.entity.Vozilo;
import com.autoservisbackend.service.VoziloService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vozila")
@RequiredArgsConstructor
public class VoziloController {

    private final VoziloService voziloService;

    @GetMapping
    public ResponseEntity<List<VoziloDTO>> getAll() {
        List<VoziloDTO> result = voziloService.getAll().stream()
                .map(voziloService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoziloDTO> getById(@PathVariable Long id) {
        return voziloService.getById(id)
                .map(voziloService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VoziloDTO> create(@RequestBody VoziloDTO dto) {
        Vozilo saved = voziloService.save(voziloService.fromDTO(dto));
        return ResponseEntity.status(201).body(voziloService.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoziloDTO> update(@PathVariable Long id, @RequestBody VoziloDTO dto) {
        if (voziloService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Vozilo vozilo = voziloService.fromDTO(dto);
        vozilo.setId(id);
        return ResponseEntity.ok(voziloService.toDTO(voziloService.save(vozilo)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (voziloService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        voziloService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return ResponseEntity.status(409)
                .body(Map.of("greska", "Vozilo sa istom registracijom ili brojem šasije već postoji."));
    }
}
