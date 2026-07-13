package com.autoservisbackend.repository;

import com.autoservisbackend.entity.Dio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DioRepository extends JpaRepository<Dio, Long> {
}