package com.kruger.inventario.service;

import java.util.List;
import com.kruger.inventario.model.Vacuna;

public interface IVacuna_S {
	List<Vacuna> listar();
	boolean guardar(Vacuna vacuna);
}