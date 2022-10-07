package com.epam.resourceservice.web;

import com.epam.commons.dto.SongContentDto;
import com.epam.resourceservice.domain.entity.SongResource;
import com.epam.resourceservice.processor.QueueSender;
import com.epam.resourceservice.processor.ResourceProcessor;
import com.epam.resourceservice.validation.Mp3Format;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/resources", produces = MediaType.APPLICATION_JSON_VALUE)
public class SongResourceController {

  private final ResourceProcessor resourceProcessor;
  private final QueueSender queueSender;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Long> upload(@RequestParam("file") @Mp3Format MultipartFile file) {
    final SongResource songResource = resourceProcessor.upload(file);
    queueSender.send(songResource.getId());
    return ResponseEntity.ok(songResource.getId());
  }

  @GetMapping("/{id}")
  public ResponseEntity<SongContentDto> download(@PathVariable long id) {
    return ResponseEntity.ok(resourceProcessor.download(id));
  }

  @DeleteMapping
  public ResponseEntity<List<Long>> delete(@RequestParam("id") @Size(max = 200) List<Long> id) {
    final List<Long> deletedIds = resourceProcessor.delete(id);
    return ResponseEntity.ok(deletedIds);
  }
}
