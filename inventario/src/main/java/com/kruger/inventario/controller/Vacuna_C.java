package com.kruger.inventario.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kruger.inventario.model.Vacuna;
import com.kruger.inventario.service.impl.VacunaServiceImpl;

@Controller
@RequestMapping("/vacuna")
public class Vacuna_C {
	
	private final String OK = "OK";
	private final String ERROR = "ERROR";
	
	@Autowired
	private VacunaServiceImpl vacunaService;
		
	@GetMapping("/listar")
	public ResponseEntity<List<Vacuna>> listar(){
		List<Vacuna> vacunas = new ArrayList<>();
		try {
			vacunas = vacunaService.listar();
			
		} catch (Exception e) {
			return new ResponseEntity<>(vacunas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vacunas, HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<HashMap<String, Object>> guardar(@RequestBody Vacuna vacuna){
		HashMap<String, Object> resultado = new HashMap<String, Object>();
		try {
			if(vacunaService.guardar(vacuna))
				resultado.put("Operacion", OK);
		} catch (Exception e) {
			resultado.put("Operacion", ERROR);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultado);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultado);
	}	

	@PutMapping("/actualizar")
	public ResponseEntity<HashMap<String, Object>> actualizar(@RequestBody Vacuna vacuna){
		HashMap<String, Object> resultado = new HashMap<String, Object>();
		try {
			if(vacunaService.guardar(vacuna))
				resultado.put("Operacion", OK);
		} catch (Exception e) {
			resultado.put("Operacion", ERROR);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultado);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultado);
	}
}