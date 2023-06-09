package com.servicio.springboot.app.prueba.cop.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tareas")
public class Tarea implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String descripcion;
	
	@NotNull
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@NotNull
	private boolean vigente;
	
	
	private Tarea() {}

	private Tarea(Long id, String descripcion, Date createAt, boolean vigente) {
		this.id = id;
		this.descripcion = descripcion;
		this.createAt = createAt;
		this.vigente = vigente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public boolean isVigente() {
		return vigente;
	}

	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}

	private static final long serialVersionUID = -7522545258455468127L;

}
