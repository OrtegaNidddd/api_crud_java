package com.registros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registros.entity.Especialidad;
import com.registros.repository.EspecialidadRepository;

import com.registros.entity.Medico;
import com.registros.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private EspecialidadRepository especialidadRepository;

	public Medico guardar(Medico request) {

		Especialidad idEspecialidad = especialidadRepository.findById(request.getEspecialidad().getId()).orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

		request.setEspecialidad(idEspecialidad);

		return medicoRepository.save(request);
	}

	public List<Medico> listar() {
		return medicoRepository.findAll();
	}

	public Medico get(Integer id) {
		return medicoRepository.findById(id).orElse(null);
	}

	public Medico update(Integer id, Medico request) {

		Optional<Medico> m = medicoRepository.findById(id);

		if (m.isPresent()) {
			Medico nuevo = m.get();

			nuevo.setNombre(request.getNombre());

			// Cambio de especialidad
			/*
			 * 
			 * if (request.getEspecialidad() != null) {
			 * nuevo.setEspecialidad(request.getEspecialidad()); }
			 * 
			 */

			return medicoRepository.save(nuevo);

		} else {
			return null;
		}
	}

	public boolean eliminar(Integer id) {
		if (medicoRepository.existsById(id)) {
			medicoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
