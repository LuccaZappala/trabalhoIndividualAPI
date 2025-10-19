package org.serratec.SerratecMusic.domain;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="artista")
@Schema(description="Representa um artista cadastrado na plataforma SerratecMusic.")
public class Artista {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Schema(description="Identificador único do artista.")
	private Long id;
	
	@NotBlank(message = "O nome precisa ser preenchido.")
	@Column
	@Schema(description="Indica o nome do artista.")
	private String nome;
	
	@NotBlank(message = "A nacionalidade do artista precisa ser preenchida.")
	@Column
	@Schema(description="Indica a nacionalidade do artista.")
	private String nacionalidade;

	
	public Artista() {
		
	}
	
	public Artista(Long id, @NotBlank(message = "O nome precisa ser preenchido.") String nome,
			@NotBlank(message = "A nacionalidade do artista precisa ser preenchida.") String nacionalidade) {
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	@ManyToMany(mappedBy = "artistas")
	@Schema(description="Lista de músicas por esse artista.")
	private List<Musica> musicas;
	
}
