package com.epam.resourceservice.processor;

import com.epam.resourceservice.domain.entity.SongContent;
import com.epam.resourceservice.domain.entity.SongResource;
import com.epam.resourceservice.service.S3StorageService;
import com.epam.resourceservice.service.SongResourceService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Service
@Transactional
public class SongResourceProcessor {

  private final S3StorageService s3StorageService;

  private final SongResourceService songResourceService;

  @Autowired
  public SongResourceProcessor(S3StorageService s3StorageService, SongResourceService songResourceService) {
    this.s3StorageService = s3StorageService;
    this.songResourceService = songResourceService;
  }

  public SongResource upload(MultipartFile song) {
    final SongResource songResource = s3StorageService.upload(song);
    return songResourceService.create(songResource);
  }

  public SongContent download(Long songId) {
    final SongResource songResource = songResourceService.findById(songId);
    return s3StorageService.download(songResource.getName());
  }

  public List<Long> delete(List<Long> songIds) {
    return songIds.stream()
        .map(this::deleteSongById)
        .filter(id -> id != 0L)
        .toList();
  }

  private Long deleteSongById(Long id) {
    try {
      final SongResource songResource = songResourceService.findById(id);
      songResourceService.delete(songResource);
      s3StorageService.delete(songResource.getName());
      return id;
    } catch (Exception e) {
      log.warn("Song with ID: {} was not deleted. {}", id, e.getMessage());
      return 0L;
    }
  }
}
