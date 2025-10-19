package org.serratec.SerratecMusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.SerratecMusic.domain.Artista;
import org.serratec.SerratecMusic.repository.ArtistaRepository;
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
@RequestMapping("/artistas")
@Tag(name="Artistas", description = "Gerencia o cadastro de artistas.")
public class ArtistaController {
	
	@Autowired
	public ArtistaRepository artistaRepository;
	
	@Operation(summary = "Lista todos os artistas.", description = "Retorna todos os artistas cadastrados.")
	@GetMapping
	public List<Artista>listar(){
		return artistaRepository.findAll();
	}
	
	@Operation(summary="Busca um artista por Id.", description = "Retorna um artista específico pelo seu identificador.")
	@GetMapping("/{id}")
	public ResponseEntity<Artista>buscarPorId(@PathVariable Long id){
		Optional<Artista>artista = artistaRepository.findById(id);
		return artista.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@Operation(summary="Insere um novo artista", description = "Adiciona um novo artista ao banco de dados.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Artista inserir(@Valid @RequestBody Artista artista) {
		return artistaRepository.save(artista);
	}
	
	@Operation(summary="Atualiza um artista", description="Atualiza as informações de um artista.")
	@PutMapping("/{id}")
	public ResponseEntity <Artista>atualizar(@Valid @RequestBody Artista artista, @PathVariable Long id) {
		if(!artistaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		artista.setId(id);
		artista = artistaRepository.save(artista);
		return ResponseEntity.ok(artista);
	}
	
	@Operation(summary="Deleta um artista.", description = "Apaga um artista do banco de dados.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>remover(@PathVariable Long id) {
		if(!artistaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		artistaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	

}
