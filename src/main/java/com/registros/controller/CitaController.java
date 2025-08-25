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

import com.registros.entity.Cita;
import com.registros.service.CitaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/citas")
public class CitaController {

	@Autowired
	private CitaService citaService;

	@PostMapping
	public ResponseEntity<Map<String, Object>> guardar(@Valid @RequestBody Cita request, BindingResult bindingResult) {

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			String errorMsg = bindingResult.getFieldError().getDefaultMessage();

			response.put("mensaje", "Error en la creacion de la cita");
			response.put("error", errorMsg);
			response.put("status", HttpStatus.BAD_REQUEST.value());

			return ResponseEntity.badRequest().body(response);
		}
		try {

			Cita creado = citaService.guardar(request);

			response.put("cita", creado);
			response.put("mensaje", "Cita creada correctamente");
			response.put("status", HttpStatus.OK.value());

			return ResponseEntity.ok(response);

		} catch (Exception e) {

			response.put("mensaje", "Error en la creacion de la cita");
			response.put("error", e.getMessage());
			response.put("status", HttpStatus.NOT_FOUND.value());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		}

	}

	@GetMapping
	public List<Cita> listar() {
		return citaService.listar();
	}

	@GetMapping("/{id}")
	public Cita listarPorId(@PathVariable Integer id) {
		return citaService.get(id);
	}
	
	@PutMapping("/{id}")
	public Cita actualizar(@PathVariable Integer id, @RequestBody Cita cita) {
		return citaService.update(id, cita);
	}


	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		citaService.eliminar(id);
	}

}
