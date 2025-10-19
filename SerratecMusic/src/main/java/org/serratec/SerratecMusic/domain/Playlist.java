package org.serratec.SerratecMusic.domain;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "playlist")
@Schema(description="Representa uma playlist cadastrada na plataforma SerratecMusic.")
public class Playlist {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Schema(description="Identificador principal da playlist.")
	private Long id;
	
	@NotBlank(message = "O nome precisa ser preenchido.")
	@Column
	@Schema(description="Indica o nome da playlist.")
	private String nome;
	
	@NotBlank(message = "A descrição precisa ser preenchida.")
	@Column
	@Schema(description="Indica a descrição da playlist.")
	private String descricao;

	public Playlist() {
		
	}
	
	public Playlist(Long id, @NotBlank(message = "O nome precisa ser preenchido.") String nome,
			@NotBlank(message = "A descrição precisa ser preenchida.") String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	@Schema(description="Indica a(s) playlist(s) de cada usuário")
	private Usuario usuario;
	
	public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
	
	@ManyToMany @JoinTable(name="playlist_musica",
	joinColumns = @JoinColumn(name="id_playlist"),
	inverseJoinColumns = @JoinColumn(name="id_musica"))
	@Schema(description="Indica a(s) música(s) da(s) playlist(s).")
	private List<Musica>musicas;
	
}
