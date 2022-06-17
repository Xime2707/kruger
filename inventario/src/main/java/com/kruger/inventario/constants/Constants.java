package com.kruger.inventario.constants;

public class Constants {
	public static final String QUERY = 
			"select p.cedula, p.nombre, p.apellido, p.correo_electronico, p.fecha_nacimiento, \r\n"
			+ " p.direccion, p.celular, p.estado_vacunacion, p.id_vacuna, v.nombre_vacuna, \\r\\n"
			+ " p.fecha_vacunacion, p.numero_dosis \r\n"
			+ " from inventario.empleado p, inventario.vacuna v \r\n";

}
