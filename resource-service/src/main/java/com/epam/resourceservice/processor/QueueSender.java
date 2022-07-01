package com.epam.resourceservice.processor;

public interface QueueSender {

  void send(Long resourceId);
}
