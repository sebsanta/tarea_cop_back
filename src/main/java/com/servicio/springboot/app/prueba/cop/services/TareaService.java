package com.servicio.springboot.app.prueba.cop.services;

import java.util.List;
import java.util.Optional;

import com.servicio.springboot.app.prueba.cop.models.entities.Tarea;

public interface TareaService {

	//busca todas las tareas
	List<Tarea> findAll();
	
	//busca una tarea mediante su id
	Optional<Tarea> findById(Long id);
	
	//guarda una tarea
	Tarea save(Tarea tarea); 
	
	//elimina una tarea
	void remove(Long id);
	
	
}
