package com.epam.resourceprocessor.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableBinding(Sink.class)
public class SpringConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
