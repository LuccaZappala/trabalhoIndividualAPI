package org.serratec.SerratecMusic.domain;

import java.util.List;

import org.serratec.SerratecMusic.enums.GeneroMusical;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "musica")
@Schema(description="Representa uma música cadastrada na plataforma SerratecMusic.")
public class Musica {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Schema(description="Identificador principal da música.")
	private Long id;
	
	@NotBlank(message = "O título precisa ser preenchido.")
	@Column
	@Schema(description="Indica o título da música.")
	private String titulo;
	
	@NotNull(message = "Os minutos precisam ser preenchidos.")
	@Column
	@Schema(description="Indica a duração da música.")
	private Integer minutos;
	
	@Enumerated(EnumType.STRING)
	@Schema(description="Indica o gênero músical da música.")
	private GeneroMusical generoMusical;

	
	public Musica() {
		
	}
	
	public Musica(Long id, @NotBlank(message = "O título precisa ser preenchido.") String titulo,
			@NotNull(message = "Os minutos precisam ser preenchidos.") Integer minutos, GeneroMusical generoMusical) {
		this.id = id;
		this.titulo = titulo;
		this.minutos = minutos;
		this.generoMusical = generoMusical;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getMinutos() {
		return minutos;
	}

	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}

	public GeneroMusical getGeneroMusical() {
		return generoMusical;
	}

	public void setGeneroMusical(GeneroMusical generoMusical) {
		this.generoMusical = generoMusical;
	}
	
	@ManyToMany @JoinTable(name="musica_artista",
	joinColumns = @JoinColumn(name="id_musica"),
	inverseJoinColumns = @JoinColumn(name="id_artista"))
	@Schema(description="Indica o(s) artista(s) dessa música.")
	private List<Artista>artistas;
	
	@ManyToMany(mappedBy="musicas")
	@Schema(description="Indica a(s) playlist(s) dessa música.")
	private List<Playlist>playlists;
	
}
