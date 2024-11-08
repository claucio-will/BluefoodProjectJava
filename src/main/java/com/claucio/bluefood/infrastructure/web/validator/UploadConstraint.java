package com.claucio.bluefood.infrastructure.web.validator;


import com.claucio.bluefood.util.FileType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = UploadValidator.class)
public @interface UploadConstraint {

    String message() default "Arquivo inválido";
    FileType[] acceptedTypes();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};


}
