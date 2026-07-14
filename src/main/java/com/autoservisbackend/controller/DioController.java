package com.autoservisbackend.controller;

import com.autoservisbackend.dto.DioDTO;
import com.autoservisbackend.entity.Dio;
import com.autoservisbackend.service.DioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dijelovi")
@RequiredArgsConstructor
public class DioController {

    private final DioService dioService;

    @GetMapping
    public ResponseEntity<List<DioDTO>> getAll() {
        List<DioDTO> result = dioService.getAll().stream()
                .map(dioService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DioDTO> getById(@PathVariable Long id) {
        return dioService.getById(id)
                .map(dioService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DioDTO> create(@RequestBody DioDTO dto) {
        Dio saved = dioService.save(dioService.fromDTO(dto));
        return ResponseEntity.status(201).body(dioService.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DioDTO> update(@PathVariable Long id, @RequestBody DioDTO dto) {
        if (dioService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Dio dio = dioService.fromDTO(dto);
        dio.setId(id);
        return ResponseEntity.ok(dioService.toDTO(dioService.save(dio)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (dioService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        dioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
