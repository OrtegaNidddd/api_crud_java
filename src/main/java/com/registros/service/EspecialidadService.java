package com.registros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registros.entity.Especialidad;
import com.registros.repository.EspecialidadRepository;

@Service
public class EspecialidadService {
	@Autowired
	private EspecialidadRepository especialidadRepository;

	public Especialidad guardar(Especialidad medico) {
		return especialidadRepository.save(medico);
	}

	public List<Especialidad> listar() {
		return especialidadRepository.findAll();
	}

	public Especialidad get(Integer id) {
		return especialidadRepository.findById(id).orElse(null);
	}

	public Especialidad update(Integer id, Especialidad request) {

		Optional<Especialidad> e = especialidadRepository.findById(id);

		if (e.isPresent()) {
			Especialidad nuevo = e.get();

			nuevo.setNombre(request.getNombre());

			return especialidadRepository.save(nuevo);

		} else {
			return null;
		}
	}

	public boolean eliminar(Integer id) {
		if (especialidadRepository.existsById(id)) {
			especialidadRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
