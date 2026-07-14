package com.autoservisbackend.controller;

import com.autoservisbackend.dto.RacunDTO;
import com.autoservisbackend.entity.Racun;
import com.autoservisbackend.service.RacunService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/racuni")
@RequiredArgsConstructor
public class RacunController {

    private final RacunService racunService;

    @GetMapping
    public ResponseEntity<List<RacunDTO>> getAll() {
        List<RacunDTO> result = racunService.getAll().stream()
                .map(racunService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RacunDTO> getById(@PathVariable Long id) {
        return racunService.getById(id)
                .map(racunService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RacunDTO> create(@RequestBody RacunDTO dto) {
        Racun saved = racunService.save(racunService.fromDTO(dto));
        return ResponseEntity.status(201).body(racunService.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RacunDTO> update(@PathVariable Long id, @RequestBody RacunDTO dto) {
        if (racunService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Racun racun = racunService.fromDTO(dto);
        racun.setId(id);
        return ResponseEntity.ok(racunService.toDTO(racunService.save(racun)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (racunService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        racunService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
