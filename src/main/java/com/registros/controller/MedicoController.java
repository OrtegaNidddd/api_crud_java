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

import com.registros.entity.Medico;
import com.registros.service.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/medico")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	@PostMapping
	public ResponseEntity<Map<String, Object>> guardar(@Valid @RequestBody Medico request,
			BindingResult bindingResult) {

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {

			String errorMsg = bindingResult.getFieldError().getDefaultMessage();

			response.put("mensaje", "Error en la creacion del medico");
			response.put("error", errorMsg);
			response.put("status", HttpStatus.BAD_REQUEST.value());

			return ResponseEntity.badRequest().body(response);

		}
		try {

			Medico creado = medicoService.guardar(request);

			response.put("medico", creado);
			response.put("mensaje", "Medico creado correctamente");
			response.put("status", HttpStatus.OK.value());

			return ResponseEntity.ok(response);

		} catch (Exception e) {

			response.put("mensaje", "Error en la creacion del paquete");
			response.put("error", e.getMessage());
			response.put("status", HttpStatus.NOT_FOUND.value());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		}

	}

	@GetMapping
	public List<Medico> listar() {
		return medicoService.listar();
	}

	@GetMapping("/{id}")
	public Medico listarPorId(@PathVariable Integer id) {
		return medicoService.get(id);
	}
	
	@PutMapping("/{id}")
	public Medico actualizar(@PathVariable Integer id, @RequestBody Medico medico) {
		return medicoService.update(id, medico);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		medicoService.eliminar(id);
	}

}
