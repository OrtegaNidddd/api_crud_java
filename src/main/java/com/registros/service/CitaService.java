package com.registros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registros.entity.Cita;
import com.registros.repository.CitaRepository;

@Service
public class CitaService {
	@Autowired
	private CitaRepository citaRepository;

	public Cita guardar(Cita cita) {
		return citaRepository.save(cita);
	}

	public List<Cita> listar() {
		return citaRepository.findAll();
	}

	public Cita get(Integer id) {
		return citaRepository.findById(id).orElse(null);
	}

	public Cita update(Integer id, Cita request) {

		Optional<Cita> c = citaRepository.findById(id);

		if (c.isPresent()) {
			Cita nuevo = c.get();

			nuevo.setEstado(request.getEstado());
			nuevo.setFechaCita(request.getFechaCita());
			nuevo.setHoraCita(request.getHoraCita());
			nuevo.setMedico(request.getMedico());
			nuevo.setPaciente(request.getPaciente());

			return citaRepository.save(nuevo);

		} else {
			return null;
		}
	}

	public boolean eliminar(Integer id) {
		if (citaRepository.existsById(id)) {
			citaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
