package com.example.carrot.domain.image.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {
    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage() {
        return ResponseEntity.ok("");
    }
}
