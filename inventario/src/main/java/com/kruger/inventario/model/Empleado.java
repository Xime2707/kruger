package com.kruger.inventario.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "empleado")
public class Empleado {

	@Id
	@Column(name = "cedula")
	private String cedula;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "correo_electronico")
	private String correo_electronico;
	
	@Column(name = "fecha_nacimiento")
	private Date fecha_nacimiento;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "estado_vacunacion")
	private String estado_vacunacion;
	
	@Column(name = "id_vacuna")
	private int id_vacuna;
	
	@Column(name = "fecha_vacunacion")
	private Date fecha_vacunacion;
	
	@Column(name = "numero_dosis")
	private int numero_dosis; 

	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombres(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellidos(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEstado_vacunacion() {
		return estado_vacunacion;
	}
	public void setEstado_vacunacion(String estado_vacunacion) {
		this.estado_vacunacion = estado_vacunacion;
	}
	public Date getFecha_vacunacion() {
		return fecha_vacunacion;
	}
	public void setFecha_vacunacion(Date fecha_vacunacion) {
		this.fecha_vacunacion = fecha_vacunacion;
	}
	public int getNumero_dosis() {
		return numero_dosis;
	}
	public void setNumero_dosis(int numero_dosis) {
		this.numero_dosis = numero_dosis;
	}
	public int getId_vacuna() {
		return id_vacuna;
	}
	public void setId_vacuna(int id_vacuna) {
		this.id_vacuna = id_vacuna;
	}
	
	@Override
	public String toString() {
		return "Empleado [cedula=" + cedula + ", nombres=" + nombre + ", apellidos=" + apellido
				+ ", correo_electronico=" + correo_electronico + ", fecha_nacimiento=" + fecha_nacimiento
				+ ", domicilio=" + direccion + ", celular=" + celular + ", estado_vacunacion=" + estado_vacunacion
			    + ", fecha_vacunacion=" + fecha_vacunacion + ", numero_dosis="
				+ numero_dosis + "]";
	}
}