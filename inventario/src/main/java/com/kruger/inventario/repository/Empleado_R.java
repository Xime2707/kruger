package com.kruger.inventario.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.kruger.inventario.model.Empleado;
import static com.kruger.inventario.constants.Constants.*;

@Repository
public interface Empleado_R extends CrudRepository<Empleado, String> {
	
	@Query(value =  QUERY +"where p.id_vacuna = v.id_vacuna and  p.estado_vacunacion = :estado_vacunacion", nativeQuery = true)
	List<Object[]> findByEstado_vacunacion(@Param("estado_vacunacion") String estado_vacunacion);

	@Query(value = QUERY +"where p.id_vacuna = v.id_vacuna and p.fecha_vacunacion between :fecha_inicio and :fecha_fin", nativeQuery = true)
	List<Object[]> findByFecha_vacunacion(@Param("fecha_inicio") Date fecha_inicio, @Param("fecha_fin") Date fecha_fin);

	@Query(value = QUERY + "where p.id_vacuna = :id_vacuna", nativeQuery = true)
	List<Object[]> findById_vacuna(@Param("id_vacuna") int id_vacuna);

	@Query(value = QUERY + "where p.id_vacuna = v.id_vacuna and v.nombre_vacuna = :nombre_vacuna", nativeQuery = true)
	List<Object[]>findByNombreVacuna(@Param("nombre_vacuna") String nombre_vacuna);

	@Query(value = QUERY + "where p.id_vacuna = v.id_vacuna", nativeQuery = true)
	List<Object[]> findByAll();
	
	@Query(value = QUERY + "where p.id_vacuna = v.id_vacuna and p.cedula = :cedula", nativeQuery = true)
	List<Object[]> findByCedula(@Param("cedula") String cedula);	
}