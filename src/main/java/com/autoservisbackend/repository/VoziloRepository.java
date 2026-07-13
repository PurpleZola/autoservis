package com.autoservisbackend.repository;

import com.autoservisbackend.entity.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoziloRepository extends JpaRepository<Vozilo, Long> {
}