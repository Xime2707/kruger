package com.kruger.inventario.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kruger.inventario.model.Empleado;
import com.kruger.inventario.model.Empleados;
import com.kruger.inventario.repository.Empleado_R;
import com.kruger.inventario.service.IEmpleado_S;
import com.kruger.inventario.utils.Validations;

@Service
public class EmpleadoServiceImpl implements IEmpleado_S {

	Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);

	@Autowired
	private Empleado_R empleadoRepository;

	@Autowired
	private Validations utils;

	@Override
	public List<Empleados> listar() {
		List<Empleados> empleados = new ArrayList<>();
		List<Object[]> eo = new ArrayList<>();
		eo = empleadoRepository.findByAll();
		llenarListaEmpleados(eo, empleados);
		return empleados;
	}

	@Override
	public HashMap<String, Object> guardar(Empleado empleado) {
		HashMap<String, Object> resultado = new HashMap<>();
		if (isValidado(empleado, "guardar")) {
			empleadoRepository.save(empleado);
			resultado = utils.generarCredendiales(empleado);
			return resultado;
		}
		return resultado;
	}

	@Override
	public List<Empleados> listarPorEstado(String estado_vacunacion) {
		List<Empleados> empleados = new ArrayList<>();
		List<Object[]> eo = new ArrayList<>();
		eo = empleadoRepository.findByEstado_vacunacion(estado_vacunacion.toUpperCase());
		llenarListaEmpleados(eo, empleados);
		return empleados;
	}

	@Override
	public List<Empleados> listarPorTipoVacuna(int id_vacuna) {
		List<Empleados> resultado = new ArrayList<>();
		List<Object[]> eo = new ArrayList<>();
		eo = empleadoRepository.findById_vacuna(id_vacuna);
		llenarListaEmpleados(eo, resultado);
		return resultado;
	}

	@Override
	public List<Empleados> listarPorNombreVacuna(String nombre_vacuna) {
		List<Empleados> resultado = new ArrayList<>();
		List<Object[]> eo = new ArrayList<>();
		eo = empleadoRepository.findByNombreVacuna(nombre_vacuna);
		llenarListaEmpleados(eo, resultado);
		return resultado;
	}

	@Override
	public List<Empleados> listarPorFecha(Date fecha_inicio, Date fecha_fin) {
		List<Empleados> resultado = new ArrayList<>();
		List<Object[]> eo = new ArrayList<>();
		eo = empleadoRepository.findByFecha_vacunacion(fecha_inicio, fecha_fin);
		llenarListaEmpleados(eo, resultado);
		return resultado;
	}

	@Override
	public Boolean eliminar(String cedula) {
		try {
			empleadoRepository.deleteById(cedula);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private Boolean isValidado(Empleado empleado, String tipo) {
		if (!utils.validarCedula(empleado.getCedula())) {
			return false;
		}
		if (!utils.validarCaracteres(empleado.getNombre())) {
			return false;
		}

		if (!utils.validarCaracteres(empleado.getApellido())) {
			return false;
		}

		if (!utils.validarCorreo(empleado.getCorreo_electronico())) {
			return false;
		}

		if ("guardar".equalsIgnoreCase(tipo)) {
			empleado.setEstado_vacunacion("NO VACUNADO");
			empleado.setCorreo_electronico("");
			empleado.setCelular("");
			empleado.setFecha_nacimiento(null);
			empleado.setFecha_vacunacion(null);
			empleado.setNumero_dosis(0);
			empleado.setDireccion("");
			empleado.setId_vacuna(1);
		} else {
			if (!utils.validarCaracteres(empleado.getDireccion())) {
				return false;
			}
			if (!utils.validarFecha(empleado.getFecha_nacimiento())) {
				return false;
			}
			if (!utils.validarNumeros(String.valueOf(empleado.getCelular()), "celular")) {
				return false;
			}
			if ("VACUNADO".equalsIgnoreCase(empleado.getEstado_vacunacion().toUpperCase())) {
				empleado.setEstado_vacunacion(empleado.getEstado_vacunacion().toUpperCase());
				if (!utils.validarNumeros(String.valueOf(empleado.getNumero_dosis()), "dosis")) {
					return false;
				}
				if (!utils.validarFecha(empleado.getFecha_vacunacion())) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public List<Empleados> listarPorCedulaE(String cedula) {
		List<Object[]> eo = new ArrayList<>();
		List<Empleados> resultado = new ArrayList<>();
		eo = empleadoRepository.findByCedula(cedula);
		llenarListaEmpleados(eo, resultado);
		return resultado;
	}

	@Override
	public HashMap<String, Object> editar(Empleado empleado) {
		HashMap<String, Object> resultado = new HashMap<>();
		if (isValidado(empleado, "editar")) {
			empleadoRepository.save(empleado);
			resultado.put("operacion","OK");
			return resultado;
		}
		return resultado;
	}

	private void llenarListaEmpleados(List<Object[]> empleadosO, List<Empleados> empleados) {
		for (Object[] var : empleadosO) {
			Empleados e = new Empleados();
			e.setCedula(var[0].toString());
			e.setNombres(var[1].toString());
			e.setApellidos(var[2].toString());
			e.setCorreo_electronico(var[3].toString());
			e.setFecha_nacimiento(var[4]==null?null:(Date.valueOf(var[4].toString())));
			e.setDireccion(var[5].toString());
			e.setCelular(var[6].toString());
			e.setEstado_vacunacion(var[7].toString());
			e.setId_vacuna(Integer.parseInt(var[8].toString()));
			e.setNombre_vacuna(var[9].toString());
			e.setFecha_vacunacion(var[10] ==null?null:(Date.valueOf(var[10].toString())));
			e.setNumero_dosis(Integer.parseInt(var[11].toString()));
			empleados.add(e);
		}
	}
}