package com.kruger.inventario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kruger.inventario.model.Rol;

@Repository
public interface Rol_R extends CrudRepository<Rol, Integer>{
	
}