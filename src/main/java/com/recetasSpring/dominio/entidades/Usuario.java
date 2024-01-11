package com.recetasSpring.dominio.entidades;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
//@Builder
//@Entity
//@Table(name="usuarios")
@SuperBuilder
public class Usuario {
	
	private Long idUsuario;
	
	@NotNull
	@NotBlank
	@Size(min=2, max=50)
	private String email;
	
	@NotBlank
	@NotNull
	@Size(min = 2, max = 40)
	private String nombre;
	
	@NotNull
	@NotBlank
	@Size(max = 20)
	private String password;
	
	@NotNull
	@NotBlank
//	@DniValido
	@Size(min = 8, max = 10)
	private String dni;
	
	@NotNull
	private Rol rol;

}
