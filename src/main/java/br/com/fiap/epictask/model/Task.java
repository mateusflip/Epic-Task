package br.com.fiap.epictask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
public class Task {
	
	//Importante estes nomes serem os mesmos da caixa de texto
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	@Size(min=10, message="Deve ter pelo menos 10 caracteres")
	private String description;
	
	@Min(value = 10, message = "A pontuação mínima é 10")
	@Max(value=500, message= "A Pontuação máxima é 500")
	private int points;

	@Min(value = 10, message = "{task.points.min}")
	@Max(value=500, message= "{task.points.max}")
	
	private int status;
	
	
}
