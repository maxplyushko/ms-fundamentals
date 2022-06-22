package com.epam.resourceservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Mp3FormatValidator implements ConstraintValidator<Mp3Format, MultipartFile> {

  private static final String MP3_EXTENSION = "mp3";

  @Override
  public void initialize(Mp3Format constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(MultipartFile song, ConstraintValidatorContext context) {
    return MP3_EXTENSION.equals(FilenameUtils.getExtension(song.getOriginalFilename()));
  }
}
