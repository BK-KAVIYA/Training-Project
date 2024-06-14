package com.training.demo.controller;

import com.training.demo.model.ImageData;
import com.training.demo.repository.ImageDataRepository;
import com.training.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageDataRepository imageDataRepository;

    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file,
                                                     @RequestParam("postId") int postId) throws IOException {
        String fileName = imageService.uploadImageToFileSystem(file);

        // Save image data to database
        ImageData imageData = new ImageData();
        imageData.setName("/uploads/" +file.getOriginalFilename());
        imageData.setType(file.getContentType());
        imageData.setPost_id(postId);

        imageDataRepository.save(imageData);

        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + fileName);
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData = imageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("/bypost/{post_id}")
    public ResponseEntity<List<ImageData>> getImagesByPostId(@PathVariable int post_id) {
        List<ImageData> images = imageDataRepository.findByPostId(post_id);
        return ResponseEntity.ok(images);
    }

    @PutMapping("/fileSystem")
    public ResponseEntity<?> updateImageToFileSystem(@RequestParam("image") MultipartFile file,
                                                     @RequestParam("postId") int postId) throws IOException {
        // Find existing image data by post ID
        List<ImageData> existingImages = imageDataRepository.findByPostId(postId);
        if (existingImages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Image data not found for post ID: " + postId);
        }

        ImageData imageData = existingImages.get(0);

        imageData.setName("/uploads/" +file.getOriginalFilename());
        imageData.setType(file.getContentType());

        imageDataRepository.save(imageData);

        String fileName = imageService.updateImageToFileSystem(file);

        return ResponseEntity.status(HttpStatus.OK).body("File updated successfully: " + fileName);
    }
}
