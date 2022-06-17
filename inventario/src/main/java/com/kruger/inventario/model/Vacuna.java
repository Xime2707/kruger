package com.kruger.inventario.model;

import javax.persistence.*;

@Entity
@Table(name = "vacuna")
public class Vacuna {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_vacuna;

	@Column(name = "nombre_vacuna")
	private String nombre_vacuna;

	public int getId_vacuna() {
		return id_vacuna;
	}
	
	public void setId_vacuna(int id_vacuna) {
		this.id_vacuna = id_vacuna;
	}

	public String getNombre_vacuna() {
		return nombre_vacuna;
	}

	public void setNombre_vacuna(String nombre_vacuna) {
		this.nombre_vacuna = nombre_vacuna;
	}
}