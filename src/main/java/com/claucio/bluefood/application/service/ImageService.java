package com.claucio.bluefood.application.service;

import com.claucio.bluefood.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {


    @Value("${bluefood.files.logotipo}")
    private String logotipoDir;

    public void uploadLogotipo(MultipartFile multipartFile, String fileName){
        try {
            IOUtils.copy(multipartFile.getInputStream(), fileName, logotipoDir);
        } catch (IOException e) {
            throw new ApplicationServiceException(e);
        }
    }
}
