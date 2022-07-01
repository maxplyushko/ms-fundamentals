package com.epam.resourceprocessor.client.impl;

import com.epam.commons.dto.SongContentDto;
import com.epam.resourceprocessor.client.ResourceServiceClient;
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
public class ResourceServiceClientImpl implements ResourceServiceClient {

  @Value("${resource-service.endpoint.url}")
  private String resourceServiceUrl;

  private final RestTemplate restTemplate;

  @Override
  @Retry(name = "resource-service")
  public SongContentDto getSongContent(String songId) {
    log.info("Attempt to get song's content from Resource Service");
    final ResponseEntity<SongContentDto> entity = restTemplate.getForEntity(
        resourceServiceUrl + "/" + songId, SongContentDto.class);
    return entity.getBody();
  }
}
