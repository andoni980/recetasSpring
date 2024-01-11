package com.recetasSpring.dominio.servicios;

import java.util.List;
import java.util.Optional;

import com.recetasSpring.dominio.entidades.Dificultad;
import com.recetasSpring.dominio.entidades.Receta;
import com.recetasSpring.dominio.entidades.Usuario;

public interface UsuarioServicios {

	List<Receta> getAllRecetas();
	
	Optional<Receta> getRecetaById(Long id);

	List<Receta> getRecetaByDificultad(Long idDificultad);

	Optional<List<Receta>> getRecetaByPartialName(String name);
	
	Usuario login(String email, String password);
	
	List<Dificultad> getAllDificultades();

	
}
