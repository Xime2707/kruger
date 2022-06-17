package com.kruger.inventario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kruger.inventario.model.Usr;

@Repository
public interface User_R extends CrudRepository<Usr, Integer>{
	Usr  findUserByUsername (@Param("username") String username);
	Usr findByCedula (@Param("cedula") String cedula);	
}