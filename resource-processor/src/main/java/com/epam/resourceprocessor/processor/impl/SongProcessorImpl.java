package com.epam.resourceprocessor.processor.impl;

import com.epam.commons.dto.SongContentDto;
import com.epam.commons.dto.SongDto;
import com.epam.resourceprocessor.client.ResourceServiceClient;
import com.epam.resourceprocessor.client.SongServiceClient;
import com.epam.resourceprocessor.parser.SongParser;
import com.epam.resourceprocessor.processor.SongProcessor;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class SongProcessorImpl implements SongProcessor {

  private final ResourceServiceClient resourceServiceClient;

  private final SongServiceClient songServiceClient;

  private final SongParser songParser;

  @Override
  public void processSong(String songId) {
    try {
      final SongContentDto songContent = resourceServiceClient.getSongContent(songId);
      final SongDto parsedSong = songParser.parse(songContent);
      songServiceClient.saveSongMetadata(parsedSong);
    } catch (Exception e) {
      log.error("Song with id: {} was not processed. {}", songId, e.getMessage());
    }
  }
}
