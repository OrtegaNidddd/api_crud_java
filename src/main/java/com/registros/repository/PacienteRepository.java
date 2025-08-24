package com.registros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registros.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

}