package com.autoservisbackend.controller;

import com.autoservisbackend.dto.KvarDTO;
import com.autoservisbackend.entity.Kvar;
import com.autoservisbackend.service.KvarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/kvarovi")
@RequiredArgsConstructor
public class KvarController {

    private final KvarService kvarService;

    @GetMapping
    public ResponseEntity<List<KvarDTO>> getAll() {
        List<KvarDTO> result = kvarService.getAll().stream()
                .map(kvarService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KvarDTO> getById(@PathVariable Long id) {
        return kvarService.getById(id)
                .map(kvarService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<KvarDTO> create(@RequestBody KvarDTO dto) {
        Kvar saved = kvarService.save(kvarService.fromDTO(dto));
        return ResponseEntity.status(201).body(kvarService.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KvarDTO> update(@PathVariable Long id, @RequestBody KvarDTO dto) {
        if (kvarService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Kvar kvar = kvarService.fromDTO(dto);
        kvar.setId(id);
        return ResponseEntity.ok(kvarService.toDTO(kvarService.save(kvar)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (kvarService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        kvarService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
