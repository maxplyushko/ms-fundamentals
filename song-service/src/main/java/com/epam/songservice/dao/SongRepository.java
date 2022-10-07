package com.epam.songservice.dao;

import com.epam.songservice.domain.entity.Song;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

  Optional<Song> findByName(String name);
}
