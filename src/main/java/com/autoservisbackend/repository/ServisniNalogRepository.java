package com.autoservisbackend.repository;

import com.autoservisbackend.entity.ServisniNalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServisniNalogRepository extends JpaRepository<ServisniNalog, Long> {
}