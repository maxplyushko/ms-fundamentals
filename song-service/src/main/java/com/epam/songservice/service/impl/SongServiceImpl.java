package com.epam.songservice.service.impl;

import com.epam.songservice.domain.entity.Song;
import com.epam.songservice.exception.SongNotFoundException;
import com.epam.songservice.dao.SongRepository;
import com.epam.songservice.service.SongService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SongServiceImpl implements SongService {

  private final SongRepository repository;

  @Autowired
  public SongServiceImpl(SongRepository repository) {
    this.repository = repository;
  }

  @Override
  public Song create(Song song) {
    return repository.save(song);
  }

  @Override
  public Song findById(Long id) {
    return repository.findById(id).orElseThrow(() -> {
      throw new SongNotFoundException(String.format("Song with id: %d was not found", id));
    });
  }

  @Override
  public void deleteById(Long id) {
    final Song song = this.findById(id);
    repository.delete(song);
  }

  @Override
  public List<Long> deleteByIds(List<Long> ids) {
    return ids.stream()
        .map(this::deleteBySongId)
        .filter(id -> id != 0L)
        .toList();
  }

  private Long deleteBySongId(Long id) {
    try {
      this.deleteById(id);
      return id;
    } catch (Exception e) {
      log.warn("Song with id: {} was not deleted. {}", id, e.getMessage());
      return 0L;
    }
  }
}
