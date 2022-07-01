package com.epam.songservice.exception;

import java.io.Serial;

public class SongNotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -6927165094347775368L;

  public SongNotFoundException() {
  }

  public SongNotFoundException(String message) {
    super(message);
  }

  public SongNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public SongNotFoundException(Throwable cause) {
    super(cause);
  }
}
