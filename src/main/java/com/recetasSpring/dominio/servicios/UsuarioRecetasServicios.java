package com.recetasSpring.dominio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.recetasSpring.datos.DificultadRepository;
import com.recetasSpring.datos.RecetasRepository;
import com.recetasSpring.dominio.entidades.Dificultad;
import com.recetasSpring.dominio.entidades.Receta;
import com.recetasSpring.dominio.entidades.Usuario;

import lombok.extern.java.Log;

@Log
@Component
@Primary
public class UsuarioRecetasServicios implements UsuarioServicios{

	protected RecetasRepository recetasRepository;
	protected DificultadRepository dificultadRepository;
	
	public UsuarioRecetasServicios(RecetasRepository recetasRepository, DificultadRepository dificultadRepository) {
		this.recetasRepository = recetasRepository;
		this.dificultadRepository = dificultadRepository;
	}
	
	
	@Override
	public List<Receta> getAllRecetas(){
		return (List<Receta>) recetasRepository.findAll();
	}
	
	@Override
	public Optional<Receta> getRecetaById(Long id) {
		return recetasRepository.findById(id);
	}
	
	@Override
	public List<Receta> getRecetaByDificultad(Long idDificultad){
		return (List<Receta>)recetasRepository.findByDificultad(idDificultad);
	}
	
	@Override
	public Optional<List<Receta>> getRecetaByPartialName(String name) {
		return recetasRepository.findByNombreContainsIgnoreCase(name).map(r->(List<Receta>)r);
	}
	
	@Override
	public Usuario login(String email, String password) {
		throw new ServiciosException("NO IMPLEMENTADO");
	}

	@Override
	public List<Dificultad> getAllDificultades() {
		return (List<Dificultad>) dificultadRepository.findAll();
	}






}
