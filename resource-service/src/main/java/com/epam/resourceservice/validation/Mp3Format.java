package com.epam.resourceservice.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = Mp3FormatValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mp3Format {

  String message() default "File is not in MP3 format";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
