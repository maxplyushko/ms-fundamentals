package com.epam.songservice.web;

import com.epam.songservice.domain.dto.SongDto;
import com.epam.songservice.domain.entity.Song;
import com.epam.songservice.mapper.SongMapper;
import com.epam.songservice.service.SongService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "/songs", produces = MediaType.APPLICATION_JSON_VALUE)
public class SongController {

  private final SongService service;
  private final SongMapper mapper;

  @Autowired
  public SongController(SongService service, SongMapper songMapper) {
    this.service = service;
    this.mapper = songMapper;
  }

  @PostMapping
  public ResponseEntity<SongDto> create(@RequestBody @Valid SongDto songDto) {
    final Song song = service.create(mapper.dtoToEntity(songDto));
    return ResponseEntity.ok(new SongDto(song.getId()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<SongDto> findById(@PathVariable Long id) {
    final Song song = service.findById(id);
    return ResponseEntity.ok(mapper.entityToDto(song));
  }

  @DeleteMapping
  public ResponseEntity<List<Long>> deleteByIds(
      @RequestParam("id") @Size(max = 200) List<Long> id) {
    return ResponseEntity.ok(service.deleteByIds(id));
  }
}
