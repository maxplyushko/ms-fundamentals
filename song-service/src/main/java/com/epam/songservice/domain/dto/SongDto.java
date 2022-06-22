package com.epam.songservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDto {

  private Long id = 0L;

  @NotBlank(message = "Name is mandatory")
  @JsonInclude(Include.NON_NULL)
  private String name;

  @NotBlank(message = "Artist's name is mandatory")
  @JsonInclude(Include.NON_NULL)
  private String artist;

  @NotBlank(message = "Album's name is mandatory")
  @JsonInclude(Include.NON_NULL)
  private String album;

  @NotBlank(message = "Song's year is mandatory")
  @JsonInclude(Include.NON_NULL)
  private String year;

  @NotNull(message = "Resource ID is mandatory")
  @JsonInclude(Include.NON_NULL)
  private Long resourceId;

  public SongDto(Long id) {
    this.id = id;
  }
}
