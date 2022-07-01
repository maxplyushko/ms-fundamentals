package com.epam.songservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {

  private String errorMessage;
  private int errorCode;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime errorTime;

  public ExceptionDto(String errorMessage, int errorCode) {
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
    this.errorTime = LocalDateTime.now();
  }
}
