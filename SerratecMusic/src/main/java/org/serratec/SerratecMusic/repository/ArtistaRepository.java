package org.serratec.SerratecMusic.repository;

import org.serratec.SerratecMusic.domain.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long>{

}
