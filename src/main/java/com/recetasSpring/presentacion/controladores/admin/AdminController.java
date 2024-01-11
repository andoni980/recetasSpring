package com.recetasSpring.presentacion.controladores.admin;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recetasSpring.dominio.entidades.Receta;
import com.recetasSpring.dominio.servicios.AdminServicios;
import com.recetasSpring.dominio.servicios.ClaveDuplicadaException;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private AdminServicios adminServicios;
	
	public AdminController(AdminServicios adminServicios) {
		this.adminServicios = adminServicios;
	}

	
	@GetMapping("/")
	public String recetas(Model modelo) {
		modelo.addAttribute("recetas", adminServicios.getAllRecetas());
		return "admin/index";
	}
	
	@GetMapping("/delete")
	public String borrar(Long id) {
		adminServicios.delete(id);
		return "redirect:/admin/";
	}
	
	@GetMapping("/receta")
	public String receta(Model modelo, Long id, Receta receta) {
		if(id != null) {
			Optional<Receta> recetaOpt = adminServicios.getRecetaById(id);
			
			if( recetaOpt.isPresent() ) {
			modelo.addAttribute("receta", recetaOpt.get());
			}
		}
		return "admin/receta";
	}
	
	@PostMapping
	public String post(Model modelo, @Valid Receta receta, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			modelo.addAttribute("alerta", "Revisa los errores en el formulario");
			modelo.addAttribute("nivel", "danger");
			
			return "admin/receta";
		}
		
		try {
			if(receta.getId() == null) {
				adminServicios.save(receta);
			}else {
				adminServicios.update(receta);
			}
		}catch(ClaveDuplicadaException e) {
			if(e.getCampo() != null) {
				modelo.addAttribute("alerta", "Revisa los errores en el formulario");
				modelo.addAttribute("nivel", "danger");
				
				bindingResult.addError(new FieldError(e.getObjeto(), e.getCampo(), e.getMessage()));
			}else {
				modelo.addAttribute("alerta", e.getMessage());
				modelo.addAttribute("nivel", "danger");
			}
			return  "admin/receta";
		}
		
		return "redirect:/admin/";
	}
	
}
