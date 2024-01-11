package com.recetasSpring.dominio.servicios;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.recetasSpring.datos.DificultadRepository;
import com.recetasSpring.datos.RecetasRepository;
import com.recetasSpring.dominio.entidades.Receta;

@Service
public class AdminRecetaServicios extends UsuarioRecetasServicios implements AdminServicios {
	
	public AdminRecetaServicios(RecetasRepository recetasRepository, DificultadRepository dificultadRepository) {
		super(recetasRepository, dificultadRepository);
	}
	
	private static final String RECETA = "receta";
	
	@Override
	public void delete(Long id) {
		recetasRepository.deleteById(id);
	}

	@Override
	public Receta save(Receta receta) {
		try {

			return recetasRepository.save(receta);
		}catch(DuplicateKeyException e) {
			String dato = e.getMessage().split("'")[1];

			if(dato.equals(receta.getNombre())) {
				throw new ClaveDuplicadaException("ese nombre ya existe en la base de datos", RECETA, "nombre", e);
			}else {
				throw new ClaveDuplicadaException("hay un campo duplicado", RECETA, null, e);
			}
		}catch(ServiciosException e){
			throw e;
		}catch(Exception e) {
			throw new ServiciosException("Error no esperado al insertar", e);
		}
	}

	@Override
	public Receta update(Receta receta) {
		return recetasRepository.save(receta);
	}

}
