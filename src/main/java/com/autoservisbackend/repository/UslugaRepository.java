package com.autoservisbackend.repository;

import com.autoservisbackend.entity.Usluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UslugaRepository extends JpaRepository<Usluga, Long> {
}