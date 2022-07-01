package com.epam.resourceservice.service;

import com.epam.commons.dto.SongContentDto;
import com.epam.resourceservice.domain.entity.SongResource;
import org.springframework.web.multipart.MultipartFile;

public interface S3StorageService {

  SongResource upload(MultipartFile multipartFile);

  SongContentDto download(String fileName);

  void delete(String songName);
}
