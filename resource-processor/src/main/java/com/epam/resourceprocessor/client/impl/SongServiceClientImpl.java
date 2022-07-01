package com.epam.resourceprocessor.client.impl;

import com.epam.commons.dto.SongDto;
import com.epam.resourceprocessor.client.SongServiceClient;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Component
@RequiredArgsConstructor
public class SongServiceClientImpl implements SongServiceClient {

  @Value("${song-service.endpoint.url}")
  private String songServiceUrl;

  private final RestTemplate restTemplate;

  @Override
  @Retry(name = "song-service")
  public SongDto saveSongMetadata(SongDto metaData) {
    log.info("Attempt to save song's metadata to Song Service");
    final ResponseEntity<SongDto> responseEntity = restTemplate.postForEntity(songServiceUrl,
        metaData, SongDto.class);
    return responseEntity.getBody();
  }
}
