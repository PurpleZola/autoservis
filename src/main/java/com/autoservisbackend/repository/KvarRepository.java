package com.autoservisbackend.repository;

import com.autoservisbackend.entity.Kvar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KvarRepository extends JpaRepository<Kvar, Long> {
}