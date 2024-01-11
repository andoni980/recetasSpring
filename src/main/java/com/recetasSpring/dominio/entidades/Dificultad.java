package com.recetasSpring.dominio.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "dificultades")
public class Dificultad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=2, max=20)
	private String nivelDificultad;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="dificultad")
	private Set<Receta> recetas = new HashSet<>();

}
