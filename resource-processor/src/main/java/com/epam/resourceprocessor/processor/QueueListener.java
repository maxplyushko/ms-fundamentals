package com.epam.resourceprocessor.processor;

import com.rabbitmq.client.Channel;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class QueueListener {

  private final SongProcessor songProcessor;

  @SneakyThrows
  @RabbitListener(queues = {"${rabbitmq.queue.name}"})
  public void listen(@Payload String resourceId, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
    log.info("Resource with ID: {} was uploaded", resourceId);
    channel.basicAck(tag, false);
    songProcessor.processSong(resourceId);
  }
}
