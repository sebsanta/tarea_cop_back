package com.servicio.springboot.app.prueba.cop.repositories;

import org.springframework.data.repository.CrudRepository;

import com.servicio.springboot.app.prueba.cop.models.entities.Tarea;



public interface TareaRepository extends CrudRepository<Tarea, Long>{

}
