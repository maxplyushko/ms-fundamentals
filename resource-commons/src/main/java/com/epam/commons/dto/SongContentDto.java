package com.epam.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongContentDto {

  private long resourceId;
  private byte[] song;

  public SongContentDto(byte[] song) {
    this.song = song;
  }
}
