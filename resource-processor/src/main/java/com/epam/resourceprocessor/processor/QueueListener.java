package com.epam.resourceprocessor.processor;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class QueueListener {

  private final SongProcessor songProcessor;

  @SneakyThrows
  @StreamListener(Sink.INPUT)
  public void listen(@Payload String resourceId) {
    log.info("Resource with ID: {} was uploaded", resourceId);
    songProcessor.processSong(resourceId);
  }
}
