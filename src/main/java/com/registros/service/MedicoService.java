package com.registros.service;

import java.util.List;

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
		return medicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Medico no encontrado"));
	}

	public Medico update(Integer id, Medico request) {

		Medico medico = medicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Medico no encontrado"));

		Especialidad idEspecialidad = especialidadRepository.findById(request.getEspecialidad().getId()).orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

		medico.setNombre(request.getNombre());
		medico.setEspecialidad(idEspecialidad);
		
		return medicoRepository.save(medico);
	
	}

	public boolean eliminar(Integer id) {
		if (medicoRepository.existsById(id)) {
			medicoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
