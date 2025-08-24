package com.registros.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "paciente")
@Data
public class Paciente {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 50, min = 3, message = "El nombre debe tener entre 3 y 50 caracteres")
	private String nombre;

	@Email(message = "El formato de email no es correcto")
	@NotBlank(message = "El email es obligatorio")
	private String email;

	@Size(max = 15, min = 10)
	@NotBlank(message = "El telefono es obligatorio")
	private String telefono;

	@NotBlank(message = "La direccion es obligatoria")
	private String direccion;

}
