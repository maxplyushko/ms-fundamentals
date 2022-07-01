package com.epam.resourceservice.service.impl;

import com.epam.resourceservice.dao.SongResourceRepository;
import com.epam.resourceservice.domain.entity.SongResource;
import com.epam.resourceservice.exception.ResourceNotFoundException;
import com.epam.resourceservice.service.SongResourceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SongResourceServiceImpl implements SongResourceService {

  private final SongResourceRepository songResourceRepository;

  @Autowired
  public SongResourceServiceImpl(SongResourceRepository songResourceRepository) {
    this.songResourceRepository = songResourceRepository;
  }

  @Override
  public SongResource create(SongResource songResource) {
    return songResourceRepository.save(songResource);
  }

  @Override
  public SongResource findById(Long id) {
    return songResourceRepository.findById(id).orElseThrow(() -> {
      throw new ResourceNotFoundException(String.format("Song with id: %d was not found", id));
    });
  }

  @Override
  public void delete(SongResource songResource) {
    songResourceRepository.delete(songResource);
  }
}
