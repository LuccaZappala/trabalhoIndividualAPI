package org.serratec.SerratecMusic.domain;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name="perfil")
@Schema(description="Representa um perfil cadastrado na plataforma SerratecMusic.")
public class Perfil {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Schema(description="Identificador único do perfil.")
	private Long id;
	
	@NotBlank(message = "O telefone precisa ser preenchido.")
	@Column
	@Schema(description="Indica o tefelone do perfil.")
	private String telefone;
	
	@Past(message = "A data de nascimento deve ser anterior à data atual!")
	@NotNull(message = "A data de nascimento precisa ser preenchida.")
	@Column
	@Schema(description="Indica a data de nascimento do perfil.")
	private LocalDate dataNascimento;


	public Perfil() {
		
	}
	
	public Perfil(Long id, @NotBlank(message = "O telefone precisa ser preenchido.") String telefone,
			@Past(message = "A data de nascimento deve ser anterior à data atual!") @NotNull(message = "A data de nascimento precisa ser preenchida.") LocalDate dataNascimento,
			Usuario usuario) {
		super();
		this.id = id;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToOne(mappedBy = "perfil")
	@Schema(description="Indica o usuário referente ao perfil.")
	private Usuario usuario;
	
}
