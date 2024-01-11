package com.recetasSpring.presentacion.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recetasSpring.bibliotecas.Alerta;
import com.recetasSpring.dominio.entidades.Receta;
import com.recetasSpring.dominio.servicios.UsuarioServicios;

@Controller
@RequestMapping
public class IndexController {
	
	@Autowired
	private UsuarioServicios usuarioServicios;
	
	@GetMapping("/")
	public String index(Model modelo) {
		modelo.addAttribute("recetas", usuarioServicios.getAllRecetas());
		modelo.addAttribute("dificultades", usuarioServicios.getAllDificultades());
		return "index";
	}
	
	@GetMapping("/{id}")
	public String recetasByDificultades(Model modelo,@PathVariable Long id) {
		modelo.addAttribute("idDificultad", id);
		modelo.addAttribute("dificultades", usuarioServicios.getAllDificultades());
		modelo.addAttribute("recetas", usuarioServicios.getRecetaByDificultad(id));
		return "indexPorDificultad";
	}
	
	@GetMapping("/receta/{id}")
	public String producto(Model modelo, @PathVariable Long id) {
		
		Optional<Receta> recetaOpt = usuarioServicios.getRecetaById(id);
		
		if(recetaOpt.isPresent()) {
			modelo.addAttribute("receta", recetaOpt.get());
		}
		return "receta";
	}
	
	@GetMapping("/login")
	public String login(String error, String logout, String noautorizado, String interactivo, Model modelo) {
		Alerta alerta = new Alerta(modelo);

		if (error != null) {
			alerta.danger("El usuario o la contraseña no son correctos");
		} else if (logout != null) {
			alerta.success("Se ha desconectado de la sesión correctamente");
		} else if(noautorizado != null) {
			alerta.danger("Tu nivel de acceso no es suficiente");
		} else if(interactivo != null) {
		} else {
			alerta.warning("Tienes que iniciar sesión");
		}

		return "login";
	}
}
