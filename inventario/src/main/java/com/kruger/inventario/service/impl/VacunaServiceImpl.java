package com.kruger.inventario.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kruger.inventario.model.Vacuna;
import com.kruger.inventario.repository.Vacuna_R;
import com.kruger.inventario.service.IVacuna_S;

@Service
public class VacunaServiceImpl implements IVacuna_S{

	@Autowired
	private Vacuna_R vacunaRepository;
	
	@Override
	public List<Vacuna> listar() {
		List<Vacuna> vacunas = new ArrayList<Vacuna>();
		vacunaRepository.findAll().forEach(vacunas::add);
		return vacunas;
	}

	@Override
	public boolean guardar(Vacuna vacuna) {
		try {
			vacunaRepository.save(vacuna);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}