package com.recetasSpring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.recetasSpring.dominio.entidades.Receta;
import com.recetasSpring.dominio.servicios.UsuarioServicios;

@SpringBootApplication
public class RecetasSpringApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RecetasSpringApplication.class, args);
	}
	
	
	@Autowired
	UsuarioServicios usuarioServicios;

	@Override
	public void run(String... args) throws Exception {
		
		
		List<Receta> recetas = usuarioServicios.getAllRecetas();
		
		for (Receta receta : recetas) {
			System.out.println(receta);
		}
		
	}

}
