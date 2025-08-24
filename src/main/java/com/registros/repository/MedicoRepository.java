package com.registros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registros.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

}