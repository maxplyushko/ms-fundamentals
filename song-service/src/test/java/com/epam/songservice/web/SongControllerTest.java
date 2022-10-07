package com.epam.songservice.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.commons.dto.SongDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SongControllerTest {

  @Autowired
  private TestRestTemplate template;

  @Test
  void testFindAll() {
    ResponseEntity<List<SongDto>> result = template.exchange("/songs", HttpMethod.GET, null,
        new ParameterizedTypeReference<>() {
        });
    assertEquals(HttpStatus.OK, result.getStatusCode());
  }
}