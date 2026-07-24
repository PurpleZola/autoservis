package com.autoservisbackend.controller;

import com.autoservisbackend.dto.KorisnikDTO;
import com.autoservisbackend.entity.Korisnik;
import com.autoservisbackend.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/korisnici")
@RequiredArgsConstructor
public class KorisnikController {

    private final KorisnikService korisnikService;

    @GetMapping
    public ResponseEntity<List<KorisnikDTO>> getAll() {
        List<KorisnikDTO> result = korisnikService.getAll().stream()
                .map(korisnikService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDTO> getById(@PathVariable Long id) {
        return korisnikService.getById(id)
                .map(korisnikService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<KorisnikDTO> create(@RequestBody KorisnikDTO dto) {
        Korisnik saved = korisnikService.save(korisnikService.fromDTO(dto));
        return ResponseEntity.status(201).body(korisnikService.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KorisnikDTO> update(@PathVariable Long id, @RequestBody KorisnikDTO dto) {
        return korisnikService.getById(id)
                .map(korisnik -> {
                    korisnik.setEmail(dto.getEmail());
                    korisnik.setRola(dto.getRola());
                    return ResponseEntity.ok(korisnikService.toDTO(korisnikService.save(korisnik)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (korisnikService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        korisnikService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
