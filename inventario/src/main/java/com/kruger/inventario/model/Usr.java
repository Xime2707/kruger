package com.kruger.inventario.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usr")
public class Usr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "cedula")
	private String cedula;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "usr_rol", joinColumns = @JoinColumn(name = "usr_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private List<Rol> roles;

	public Usr(Usr users) {
	}

	public Usr() {
		
	}
	public Usr(String username, String password, String id_empleado, List<Rol> roles) {
		this.username = username;
		this.password = password;
		this.cedula = id_empleado;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId_empleado() {
		return cedula;
	}

	public void setId_empleado(String id_empleado) {
		this.cedula = id_empleado;
	}
}