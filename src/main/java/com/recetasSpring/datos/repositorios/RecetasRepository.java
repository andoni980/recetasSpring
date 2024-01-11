package com.recetasSpring.datos.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.recetasSpring.dominio.entidades.Receta;

public interface RecetasRepository extends CrudRepository<Receta, Long> {

	Optional<Receta> findByNombre(String nombre);
	
	@Query(nativeQuery = true,value = "SELECT * FROM recetas WHERE dificultad_id = ?")
	Iterable<Receta> findByDificultad(Long id);

	Optional<List<Receta>> findByNombreContainsIgnoreCase(String name);
	
//	@Query("select count(r) from Receta r")
//	long getRecetasCount();
}
