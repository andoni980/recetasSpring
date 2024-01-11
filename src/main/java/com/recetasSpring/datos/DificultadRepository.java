package com.recetasSpring.datos;

import org.springframework.data.repository.CrudRepository;

import com.recetasSpring.dominio.entidades.Dificultad;

public interface DificultadRepository extends CrudRepository<Dificultad, Long> {
	
//	@Query("from Dificultad d join fetch d.recetas where d.id=:id")
//	Dificultad getDificultadConRecetas(@Param("id") Long id);
}
