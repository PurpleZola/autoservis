package com.autoservisbackend.repository;

import com.autoservisbackend.entity.Racun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacunRepository extends JpaRepository<Racun, Long> {
}