package com.example.carrot.domain.image.controller;

import com.example.carrot.domain.image.service.ImageService;
import com.example.carrot.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile multipartFile, @AuthenticationPrincipal User user) throws IOException {
        imageService.save(multipartFile, user.getId());
        return ResponseEntity.ok("이미지 저장 완료");
    }

    @GetMapping("/images")
    public ResponseEntity<?> getImageList(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(imageService.findAll(user.getId()));
    }
}
