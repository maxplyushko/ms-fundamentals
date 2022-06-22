package com.epam.resourceservice.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 6296027244903761218L;

  public ResourceNotFoundException() {
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceNotFoundException(Throwable cause) {
    super(cause);
  }
}
