package com.servicio.springboot.app.prueba.cop.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.springboot.app.prueba.cop.models.entities.Tarea;
import com.servicio.springboot.app.prueba.cop.services.TareaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin
@Api(value="Crud de tareas")
@RestController
@RequestMapping("/tareas")
public class TareaController {
	
	@Autowired
	private TareaService service;

	@ApiOperation(value="Devuelve la lista completa de tareas", response=List.class)
	@GetMapping
	public List<Tarea> list(){
		return service.findAll();
	}
	
	@ApiOperation(value="Devuelve la información de una tarea mediante su id", response=Tarea.class)
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Optional<Tarea> tareaOptional = service.findById(id);
		if(tareaOptional.isPresent()) {
			return ResponseEntity.ok(tareaOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value="Añade una nueva tarea")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) //201 created
	public ResponseEntity<?> create(@Valid @RequestBody Tarea tarea, BindingResult result) {
		if(result.hasErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(tarea));
	}
	
	//@PostMapping
	//public ResponseEntity<?> crear(@RequestBody Tarea tarea) {
	//	return  ResponseEntity.status(HttpStatus.CREATED).body(service.save(tarea)); //cuerpo de la respuesta se guarda el objeto Tarea
	//}
	
	@ApiOperation(value="Busca una tarea y su existe (isPresent) modifica una tarea buscándola mediante su id")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Tarea tarea, BindingResult result, @PathVariable Long id){
		if(result.hasErrors()) {
			return validation(result);
		}
		Optional<Tarea> o = service.findById(id);
		if(o.isPresent()) {
			Tarea tareadb = o.orElseThrow();
			tareadb.setDescripcion(tarea.getDescripcion());
			tareadb.setVigente(tarea.isVigente());
			tareadb.setCreateAt(tarea.getCreateAt());
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(tareadb));
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value="Busca una tarea y si existe (isPresent) elimina una tarea mediante su identificador")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@ApiParam(value = "identificador de la tarea a eliminar")@PathVariable Long id){
		Optional<Tarea> o = service.findById(id);
		if(o.isPresent()) {
			service.remove(id);
			return ResponseEntity.noContent().build(); //204 no tiene contenido
		}
		return ResponseEntity.notFound().build();
	}
	
	
	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		
		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
	
	
	
}
