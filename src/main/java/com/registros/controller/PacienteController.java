package com.registros.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.registros.entity.Paciente;
import com.registros.service.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/paciente")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	@PostMapping
	public ResponseEntity<Map<String, Object>> guardar(@Valid @RequestBody Paciente request,
			BindingResult bindingResult) {

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			String errorMsg = bindingResult.getFieldError().getDefaultMessage();

			response.put("mensaje", "Error en la creacion del paciente");
			response.put("error", errorMsg);
			response.put("status", HttpStatus.BAD_REQUEST.value());

			return ResponseEntity.badRequest().body(response);
		}
		try {

			Paciente creado = pacienteService.guardar(request);

			response.put("paciente", creado);
			response.put("mensaje", "Paciente creado correctamente");
			response.put("status", HttpStatus.OK.value());

			return ResponseEntity.ok(response);

		} catch (Exception e) {

			response.put("mensaje", "Error en la creacion de la especialidad");
			response.put("error", e.getMessage());
			response.put("status", HttpStatus.NOT_FOUND.value());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		}

	}

	@GetMapping
	public List<Paciente> listar() {
		return pacienteService.listar();
	}

	
	@GetMapping("/{id}")
	public Paciente listarPorId(@PathVariable Integer id) {
		return pacienteService.get(id);
	}

	@PutMapping("/{id}")
	public Paciente actualizar(@PathVariable Integer id, @RequestBody Paciente paciente) {
		return pacienteService.update(id, paciente);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		pacienteService.eliminar(id);
	}

}
