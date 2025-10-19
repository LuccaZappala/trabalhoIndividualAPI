package org.serratec.SerratecMusic.repository;

import org.serratec.SerratecMusic.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

}
