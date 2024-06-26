package com.training.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    private static final String UPLOAD_DIR = "E:/Training Project/front-end/sample-app/public/uploads/";

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Get the file path
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        Path path = Paths.get(filePath);

        Files.write(path, file.getBytes());

        return "File uploaded successfully: " + filePath;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        String filePath = UPLOAD_DIR + fileName;
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }

    public String updateImageToFileSystem(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = UPLOAD_DIR + fileName;
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());
        return fileName;
    }

    public void deleteImageFromFileSystem(String fileName) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.deleteIfExists(path);
    }
}
