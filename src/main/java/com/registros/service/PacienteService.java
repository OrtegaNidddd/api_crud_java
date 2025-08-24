package com.registros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registros.entity.Paciente;
import com.registros.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	public Paciente guardar(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	public List<Paciente> listar() {
		return pacienteRepository.findAll();
	}

	public Paciente get(Integer id) {
		return pacienteRepository.findById(id).orElse(null);
	}

	public Paciente update(Integer id, Paciente request) {

		Optional<Paciente> p = pacienteRepository.findById(id);

		if (p.isPresent()) {
			Paciente nuevo = p.get();

			nuevo.setNombre(request.getNombre());
			nuevo.setEmail(request.getEmail());
			nuevo.setTelefono(request.getTelefono());
			nuevo.setDireccion(request.getDireccion());

			return pacienteRepository.save(nuevo);

		} else {
			return null;
		}
	}

	public boolean eliminar(Integer id) {
		if (pacienteRepository.existsById(id)) {
			pacienteRepository.deleteById(id);
			return true;
		}
		return false;
	}

}