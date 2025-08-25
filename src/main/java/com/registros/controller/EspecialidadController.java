package com.registros.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registros.entity.Especialidad;
import com.registros.service.EspecialidadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/especialidad")
public class EspecialidadController {

	@Autowired
	private EspecialidadService especialidadService;

	@PostMapping
	public ResponseEntity<Map<String, Object>> guardar(@Valid @RequestBody Especialidad request,
			BindingResult bindingResult) {

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			String errorMsg = bindingResult.getFieldError().getDefaultMessage();

			response.put("mensaje", "Error en la creacion de la especialidad");
			response.put("error", errorMsg);
			response.put("status", HttpStatus.BAD_REQUEST.value());

			return ResponseEntity.badRequest().body(response);
		}
		try {

			Especialidad creado = especialidadService.guardar(request);

			response.put("especialidad", creado);
			response.put("mensaje", "Especialidad creada correctamente");
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
	public List<Especialidad> listar() {
		return especialidadService.listar();
	}

	@GetMapping("/{id}")
	public Especialidad listarPorId(@PathVariable Integer id) {
		return especialidadService.get(id);
	}
	
	@PutMapping("/{id}")
	public Especialidad actualizar(@PathVariable Integer id, @RequestBody Especialidad especialidad) {
		return especialidadService.update(id, especialidad);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		especialidadService.eliminar(id);
	}

}
