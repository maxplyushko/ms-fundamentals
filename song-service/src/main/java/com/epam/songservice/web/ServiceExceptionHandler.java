package com.epam.songservice.web;

import com.epam.commons.dto.ExceptionDto;
import com.epam.songservice.exception.SongNotFoundException;
import java.util.List;
import javax.xml.bind.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class ServiceExceptionHandler extends DefaultHandlerExceptionResolver {

  private static final int NOT_FOUND_CUSTOM_CODE = 40401;
  private static final int BAD_REQUEST_CUSTOM_CODE = 40001;
  private static final int ERROR_CUSTOM_CODE = 50001;

  @ExceptionHandler(SongNotFoundException.class)
  public ResponseEntity<ExceptionDto> handleResourceNotFound(SongNotFoundException e) {
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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ExceptionDto>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    final List<ExceptionDto> exceptionDtos = e.getBindingResult().getAllErrors().stream()
        .map(error -> new ExceptionDto(error.getDefaultMessage(), BAD_REQUEST_CUSTOM_CODE))
        .toList();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDtos);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDto> handleException(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
        new ExceptionDto(e.getMessage(), ERROR_CUSTOM_CODE)
    );
  }
}
