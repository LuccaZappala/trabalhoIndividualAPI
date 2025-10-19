package org.serratec.SerratecMusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.SerratecMusic.domain.Musica;
import org.serratec.SerratecMusic.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/musicas")
@Tag(name="Músicas", description = "Gerencia o cadastro de músicas.")
public class MusicaController {
	
	@Autowired
	public MusicaRepository musicaRepository;
	
	@Operation(summary = "Lista todas as músicas.", description = "Retorna todas as músicas cadastradas.")
	@GetMapping
	public List<Musica>listar(){
		return musicaRepository.findAll();
	}
	
	@Operation(summary="Busca uma música por Id.", description = "Retorna uma música específica pelo seu identificador.")
	@GetMapping("/{id}")
	public ResponseEntity<Musica>buscarPorId(@PathVariable Long id) {
		Optional<Musica>musica = musicaRepository.findById(id);
		return musica.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@Operation(summary="Insere uma nova música", description = "Adiciona uma nova música ao banco de dados.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Musica inserir(@Valid @RequestBody Musica musica) {
		return musicaRepository.save(musica);
	}
	
	@Operation(summary="Atualiza uma música", description="Atualiza as informações de uma música.")
	@PutMapping("/{id}")
	public ResponseEntity<Musica>atualizar(@Valid @RequestBody Musica musica, @PathVariable Long id) {
		if(!musicaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		musica.setId(id);
		musica = musicaRepository.save(musica);
		return ResponseEntity.ok(musica);
	}
	
	@Operation(summary="Deleta uma música.", description = "Apaga uma música do banco de dados.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>remover(@PathVariable Long id) {
		if(!musicaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		musicaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
