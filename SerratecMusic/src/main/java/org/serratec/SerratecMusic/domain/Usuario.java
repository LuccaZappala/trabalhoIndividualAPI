package org.serratec.SerratecMusic.domain;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name= "usuario")
@Schema(description="Representa um usuário cadastrado na plataforma SerratecMusic.")
public class Usuario {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Schema(description="Identificador único do usuário.")
	private Long id;
	
	@NotBlank(message = "O nome precisa ser preenchido.")
	@Column
	@Schema(description="Indica o nome do usuário.")
	private String nome;
	
	@Email(message = "Email inválido!")
	@NotBlank(message = "O email precisa ser preenchido.")
	@Column
	@Schema(description="Indica o email do usuário.")
	private String email;

	public Usuario() {
		
	}
	
	public Usuario(Long id, @NotBlank(message = "O nome precisa ser preenchido.") String nome,
			@Email(message = "Email inválido!") @NotBlank(message = "O email precisa ser preenchido.") String email,
			Perfil perfil, List<Playlist> playlists) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.perfil = perfil;
		this.playlists = playlists;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@OneToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name = "id_perfil")
	@Schema(description="Indica o perfil do usuário.")
	private Perfil perfil;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@Schema(description="Indica a(s) playlist(s) do usuário.")
	private List<Playlist>playlists;
	
}
