package com.servicio.springboot.app.prueba.cop;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringbootServicioPruebaCopApplicationTests {

	
	@Autowired
	MockMvc mock;
	
	
	@Test
	@Order(4)
	void eliminarTareas() throws Exception {
		mock.perform(delete("/tareas/6"))
		.andDo(print());
	}
	
	@Test
	@Order(5)
	void testTareas3() throws Exception {
		mock.perform(get("/tareas")).andDo(print());
	}
	
	@Test
	@Order(3)
	void testTareas() throws Exception {
		mock.perform(get("/tareas")).andDo(print());
	}
	
	@Test
	@Order(1)
	void testTareas2() throws Exception {
		mock.perform(get("/tareas")).andDo(print());
	}
	
	@Test
	@Order(2)
	void testActualizar() throws Exception {
		 mock.perform(put("/tareas/6")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"descripcion\":\"basket\",\"createAt\":\"2021-01-01\",\"vigente\":true}"))
			.andDo(print());
	}
	
	@Test
	@Order(0)
	void testAgregar() throws Exception {
		mock.perform(post("/tareas")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"descripcion\":\"rugby\",\"createAt\":\"2022-01-01\",\"vigente\":true}"))
		.andDo(print());
	}

}
