package com.kruger.inventario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kruger.inventario.model.Vacuna;

@Repository
public interface Vacuna_R extends CrudRepository<Vacuna, Integer> {
}