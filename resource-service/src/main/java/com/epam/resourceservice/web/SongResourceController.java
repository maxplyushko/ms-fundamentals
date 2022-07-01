package com.epam.resourceservice.web;

import com.epam.resourceservice.domain.entity.SongContent;
import com.epam.resourceservice.domain.entity.SongResource;
import com.epam.resourceservice.processor.SongResourceProcessor;
import com.epam.resourceservice.validation.Mp3Format;
import java.util.List;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/resources", produces = MediaType.APPLICATION_JSON_VALUE)
public class SongResourceController {

  private final SongResourceProcessor processor;

  @Autowired
  public SongResourceController(SongResourceProcessor processor) {
    this.processor = processor;
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Long> upload(@RequestParam("file") @Mp3Format MultipartFile file) {
    final SongResource songResource = processor.upload(file);
    return ResponseEntity.ok(songResource.getId());
  }

  @GetMapping("/{id}")
  public ResponseEntity<SongContent> download(@PathVariable long id) {
    return ResponseEntity.ok(processor.download(id));
  }

  @DeleteMapping
  public ResponseEntity<List<Long>> delete(@RequestParam("id") @Size(max = 200) List<Long> id) {
    final List<Long> deletedIds = processor.delete(id);
    return ResponseEntity.ok(deletedIds);
  }
}
