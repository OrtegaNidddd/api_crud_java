package com.registros.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "citas")
@Data
public class Cita {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "El estado es obligatorio")
	private Integer estado;

	@NotNull(message = "La fecha de la cita es obligatoria")
	private LocalDate fechaCita;

	// Auto with prePersist
	private LocalDateTime fechaAsignacion;

	@NotNull(message = "La hora de la cita es obligatoria")
	private LocalTime horaCita;

	// FK
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "paciente", nullable = false)
	private Paciente paciente;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "medico", nullable = false)
	private Medico medico;

	@PrePersist
	public void prePersist() {

		this.fechaAsignacion = LocalDateTime.now();

	}
}
