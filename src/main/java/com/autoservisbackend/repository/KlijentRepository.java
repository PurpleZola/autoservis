package com.autoservisbackend.repository;

import com.autoservisbackend.entity.Klijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlijentRepository extends JpaRepository<Klijent, Long> {
}