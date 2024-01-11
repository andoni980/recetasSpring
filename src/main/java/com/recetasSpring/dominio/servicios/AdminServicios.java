package com.recetasSpring.dominio.servicios;

import com.recetasSpring.dominio.entidades.Receta;

public interface AdminServicios extends UsuarioServicios {
	
	Receta save(Receta receta);
	Receta update(Receta objet);
	void delete(Long id);
}
