package com.servicio.springboot.app.prueba.cop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicio.springboot.app.prueba.cop.models.entities.Tarea;
import com.servicio.springboot.app.prueba.cop.repositories.TareaRepository;


@Service
public class TareaServiceImpl implements TareaService{
	
	@Autowired
	private TareaRepository repository;

	@Override
	@Transactional(readOnly=true)
	public List<Tarea> findAll() {
		return (List<Tarea>) repository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Tarea> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Tarea save(Tarea tarea) {
		return repository.save(tarea);
	}

	@Override
	@Transactional
	public void remove(Long id) {
		repository.deleteById(id);
		
	}

}
