package org.serratec.SerratecMusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.SerratecMusic.domain.Playlist;
import org.serratec.SerratecMusic.repository.PlaylistRepository;
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
@RequestMapping("/playlists")
@Tag(name="Playlists", description = "Gerencia o cadastro de playlists.")
public class PlaylistController {
	
	@Autowired
	public PlaylistRepository playlistRepository;
	
	@Operation(summary = "Lista todas as playlists.", description = "Retorna todas as playlists cadastradas.")
	@GetMapping
	public List<Playlist>listar() {
		return playlistRepository.findAll();
	}
	
	@Operation(summary="Busca uma playlist por Id.", description = "Retorna uma playlist específica pelo seu identificador.")
	@GetMapping("/{id}")
	public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) {
	    Optional<Playlist> playlist = playlistRepository.findById(id);
	    return playlist.map(ResponseEntity::ok)
	                  .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@Operation(summary="Insere uma nova playlist", description = "Adiciona uma nova playlist ao banco de dados.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Playlist inserir(@Valid @RequestBody Playlist playlist) {
	    return playlistRepository.save(playlist);
	}
	
	@Operation(summary="Atualiza uma playlist", description="Atualiza as informações de uma playlist.")
	@PutMapping("/{id}")
	public ResponseEntity<Playlist> atualizar(@Valid @RequestBody Playlist playlist, @PathVariable Long id) {
	    if (!playlistRepository.existsById(id)) {
	        return ResponseEntity.notFound().build();
	    }
	    playlist.setId(id);
	    playlist = playlistRepository.save(playlist);
	    return ResponseEntity.ok(playlist);
	}
	
	@Operation(summary="Deleta uma playlist.", description = "Apaga uma playlist do banco de dados.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
	    if (!playlistRepository.existsById(id)) {
	        return ResponseEntity.notFound().build();
	    }
	    playlistRepository.deleteById(id);
	    return ResponseEntity.noContent().build();
	}

}
