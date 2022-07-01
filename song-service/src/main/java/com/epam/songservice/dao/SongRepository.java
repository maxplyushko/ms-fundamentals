package com.epam.songservice.dao;

import com.epam.songservice.domain.entity.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

}
