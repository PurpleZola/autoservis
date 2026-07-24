package com.autoservisbackend.controller;

import com.autoservisbackend.dto.KlijentDTO;
import com.autoservisbackend.entity.Klijent;
import com.autoservisbackend.service.KlijentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/klijenti")
@RequiredArgsConstructor
public class KlijentController {

    private final KlijentService klijentService;

    @GetMapping
    public ResponseEntity<List<KlijentDTO>> getAll() {
        List<KlijentDTO> result = klijentService.getAll().stream()
                .map(klijentService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KlijentDTO> getById(@PathVariable Long id) {
        return klijentService.getById(id)
                .map(klijentService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<KlijentDTO> create(@RequestBody KlijentDTO dto) {
        Klijent saved = klijentService.save(klijentService.fromDTO(dto));
        return ResponseEntity.status(201).body(klijentService.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KlijentDTO> update(@PathVariable Long id, @RequestBody KlijentDTO dto) {
        if (klijentService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Klijent klijent = klijentService.fromDTO(dto);
        klijent.setId(id);
        return ResponseEntity.ok(klijentService.toDTO(klijentService.save(klijent)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (klijentService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        klijentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return ResponseEntity.status(409)
                .body(Map.of("greska", "Ovaj korisnik je već povezan sa drugim klijentom."));
    }
}
