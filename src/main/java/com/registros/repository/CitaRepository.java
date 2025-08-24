package com.registros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registros.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

}