package com.epam.resourceservice.web;

import com.epam.resourceservice.domain.dto.ExceptionDto;
import com.epam.resourceservice.exception.ResourceNotFoundException;
import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class ServiceExceptionHandler extends DefaultHandlerExceptionResolver {

  private static final int NOT_FOUND_CUSTOM_CODE = 40401;
  private static final int BAD_REQUEST_CUSTOM_CODE = 40001;
  private static final int ERROR_CUSTOM_CODE = 50001;

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ExceptionDto> handleResourceNotFound(ResourceNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        new ExceptionDto(e.getMessage(), NOT_FOUND_CUSTOM_CODE)
    );
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ExceptionDto> handleValidationException(ValidationException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        new ExceptionDto(e.getMessage(), BAD_REQUEST_CUSTOM_CODE)
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDto> handleException(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
        new ExceptionDto(e.getMessage(), ERROR_CUSTOM_CODE)
    );
  }
}
