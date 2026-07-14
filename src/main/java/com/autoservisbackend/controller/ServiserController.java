package com.autoservisbackend.controller;

import com.autoservisbackend.dto.ServiserDTO;
import com.autoservisbackend.entity.Serviser;
import com.autoservisbackend.service.ServiserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/serviseri")
@RequiredArgsConstructor
public class ServiserController {

    private final ServiserService serviserService;

    @GetMapping
    public ResponseEntity<List<ServiserDTO>> getAll() {
        List<ServiserDTO> result = serviserService.getAll().stream()
                .map(serviserService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiserDTO> getById(@PathVariable Long id) {
        return serviserService.getById(id)
                .map(serviserService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServiserDTO> create(@RequestBody ServiserDTO dto) {
        Serviser saved = serviserService.save(serviserService.fromDTO(dto));
        return ResponseEntity.status(201).body(serviserService.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiserDTO> update(@PathVariable Long id, @RequestBody ServiserDTO dto) {
        if (serviserService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Serviser serviser = serviserService.fromDTO(dto);
        serviser.setId(id);
        return ResponseEntity.ok(serviserService.toDTO(serviserService.save(serviser)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (serviserService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        serviserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
