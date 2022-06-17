package com.kruger.inventario.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.kruger.inventario.model.Empleado;
import com.kruger.inventario.model.Empleados;

public interface IEmpleado_S {
	List<Empleados> listar();
	HashMap<String, Object> guardar(Empleado empleado);
	List<Empleados>listarPorFecha(Date fecha_inicio, Date fecha_fin);
	Boolean eliminar(String cedula);
	List<Empleados> listarPorTipoVacuna(int id_vacuna);
	List<Empleados> listarPorEstado(String estado_vacunacion);
	List<Empleados> listarPorNombreVacuna(String nombre_vacuna);
	List<Empleados> listarPorCedulaE(String cedula);
	HashMap<String, Object> editar(Empleado empleado);
}