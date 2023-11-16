package com.liashenko.applicant.service;

import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.IOException;
import java.io.InputStream;

@Data
@Service
public class FileService {

    private final String baseUrl;

    public FileService() {
        this.baseUrl = "static/documents/";
    }

    public InputFile getFileByPath(String path) throws IOException {
        String fullPath = this.baseUrl + path;
        Resource resource = new ClassPathResource(fullPath);
        InputStream stream = resource.getInputStream();

        return new InputFile(stream, path);
    }
}
