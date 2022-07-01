package com.epam.resourceservice.processor.impl;

import com.epam.resourceservice.processor.QueueSender;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SongQueueSender implements QueueSender {

  private final RabbitTemplate rabbitTemplate;

  private final Queue queue;

  @Override
  public void send(Long resourceId) {
    rabbitTemplate.convertAndSend(queue.getName(), resourceId);
  }
}
