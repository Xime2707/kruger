package com.kruger.inventario.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kruger.inventario.model.Empleado;
import com.kruger.inventario.model.Empleados;
import com.kruger.inventario.service.IEmpleado_S;


@Controller
@RequestMapping("/empleado")
public class Empleado_C {
	
	private static final String OK = "OK";
	private static final Object ERROR = "ERROR";
	
	@Autowired
	private IEmpleado_S empleadoS;
	
	@GetMapping("/listar")
	@ResponseBody
	public ResponseEntity<List<Empleados>> listar(){
		List<Empleados> empleados = new ArrayList<>();
		try {
			empleados = empleadoS.listar();
			
		} catch (Exception e) {
			return new ResponseEntity<>(empleados, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(empleados, HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<HashMap<String, Object>> guardar(@RequestBody Empleado empleado
			){
		HashMap<String, Object> respuesta = new HashMap<String, Object>();
		try {
			respuesta = empleadoS.guardar(empleado);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	}
	
	@PutMapping("/editar")
	public ResponseEntity<HashMap<String, Object>> editar(@RequestBody Empleado empleado){
		HashMap<String, Object> respuesta = new HashMap<String, Object>();
		try {
			respuesta = empleadoS.editar(empleado);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	}
	
	@GetMapping("/listarPorEstado")
	@ResponseBody
	public ResponseEntity<List<Empleados>> listarPorEstado(@RequestParam("estado_vacunacion") String estado_vacunacion){
		List<Empleados> respuesta = new ArrayList<>();
		try {
			respuesta= empleadoS.listarPorEstado(estado_vacunacion);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		} 
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		
	}
	
	@GetMapping("/listarPorFecha")
	@ResponseBody
	public ResponseEntity<List<Empleados>> listarPorFecha(@RequestParam("fecha_inicio") String fecha_inicio,
			@RequestParam("fecha_fin") String fecha_fin){
		List<Empleados> respuesta = new ArrayList<>();
		Date fecha_in = null;
		Date fecha_fn = null;
		try {
			fecha_in = Date.valueOf(fecha_inicio);  
			fecha_fn = Date.valueOf(fecha_fin);
			
		} catch (Exception e) {
			 
		}
		
		try {
			respuesta= empleadoS.listarPorFecha(fecha_in, fecha_fn);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		} 
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		
	}
	
	@GetMapping("/listarPorIdVacuna")
	@ResponseBody
	public ResponseEntity<List<Empleados>> listarPorTipoVacuna(@RequestParam("id_vacuna") int id_vacuna){
		List<Empleados> respuesta = new ArrayList<>();		
		try {
			respuesta= empleadoS.listarPorTipoVacuna(id_vacuna);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		} 
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		
	}

	
	@GetMapping("/listarPorCedulaE")
	@ResponseBody
	public ResponseEntity<List<Empleados>> listarPorCedEmpleado(@RequestParam("cedula") String cedula){
		List<Empleados> respuesta = new ArrayList<>();	
		try {
			respuesta= empleadoS.listarPorCedulaE(cedula);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		} 
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		
	}
	
	@GetMapping("/listarPorNombreVacuna")
	@ResponseBody
	public ResponseEntity<List<Empleados>> listarPorNombreVacuna(@RequestParam("nombre_vacuna") String nombre_vacuna){
		List<Empleados> respuesta = new ArrayList<>();		
		try {
			respuesta= empleadoS.listarPorNombreVacuna(nombre_vacuna);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		} 
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	}
	
	@DeleteMapping("/eliminar/{cedula}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> eliminar(@PathVariable("cedula") String cedula){

		HashMap<String, Object> respuesta = new HashMap<String, Object>();
		try {
			if (empleadoS.eliminar(cedula))
				respuesta.put("operacion", OK);
			else
				respuesta.put("operacion", ERROR);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		} 
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	}
}
