package org.serratec.SerratecMusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.SerratecMusic.domain.Playlist;
import org.serratec.SerratecMusic.domain.Usuario;
import org.serratec.SerratecMusic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Tag(name="Usuários", description = "Gerencia o cadastro de usuários.")
public class UsuarioController {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@Operation(summary = "Lista todos os usuários.", description = "Retorna todos os usuários cadastrados.")
	@GetMapping
	public List<Usuario>listar(){
		return usuarioRepository.findAll();
	}
	
	@Operation(summary="Busca um usuário por Id.", description = "Retorna um usuário específico pelo seu identificador.")
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
	    Optional<Usuario> usuario = usuarioRepository.findById(id);
	    return usuario.map(ResponseEntity::ok)
	                  .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@Operation(summary="Insere um novo usuário", description = "Adiciona um novo usuário ao banco de dados.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario inserir(@Valid @RequestBody Usuario usuario) {
		
		if (usuario.getPerfil() != null) {
	        usuario.getPerfil().setUsuario(usuario); 
	    }

	    if (usuario.getPlaylists() != null) {
	        for (Playlist playlist : usuario.getPlaylists()) {
	            playlist.setUsuario(usuario); 
	        }
	    }

	    return usuarioRepository.save(usuario);
	}
	
	@Operation(summary="Atualiza um usuário", description="Atualiza as informações de um usuário.")
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario usuario, @PathVariable Long id) {
	    if (!usuarioRepository.existsById(id)) {
	        return ResponseEntity.notFound().build();
	    }
	    usuario.setId(id);
	    usuario = usuarioRepository.save(usuario);
	    return ResponseEntity.ok(usuario);
	}
	
	@Operation(summary="Deleta um usuário.", description = "Apaga um usuário do banco de dados.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
	    if (!usuarioRepository.existsById(id)) {
	        return ResponseEntity.notFound().build();
	    }
	    usuarioRepository.deleteById(id);
	    return ResponseEntity.noContent().build();
	}

}
