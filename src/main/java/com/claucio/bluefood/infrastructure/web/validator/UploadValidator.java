package com.claucio.bluefood.infrastructure.web.validator;

import com.claucio.bluefood.util.FileType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class UploadValidator implements ConstraintValidator<UploadConstraint, MultipartFile> {

    private List<FileType> acceptedFileTypes;

    @Override
    public void initialize(UploadConstraint constraintAnnotation) {
        acceptedFileTypes = Arrays.asList(constraintAnnotation.acceptedTypes());
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null) {
            return true;
        }
        for (FileType fileType : acceptedFileTypes) {
            if (fileType.sameOf(multipartFile.getContentType())) {
                return true;
            }
        }
        return false;
    }
}
