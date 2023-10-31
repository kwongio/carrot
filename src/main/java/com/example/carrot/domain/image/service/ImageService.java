package com.example.carrot.domain.image.service;

import com.example.carrot.domain.image.domain.Image;
import com.example.carrot.domain.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    static final String FILEPATH = "C:\\jj\\carrot\\src\\main\\resources\\images\\";

    @Transactional
    public void save(MultipartFile multipartFile, Long userId) throws IOException {
        if(multipartFile.isEmpty()) {
            throw new ImageSaveFailException();
        }
        File file = new File(FILEPATH);
        file.mkdirs();
        String[] ext = multipartFile.getOriginalFilename().split("\\.");

        if (!extValidationCheck(ext[1])) {
            throw new ImageSaveFailException();
        }

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_DD_ss_SSSS"));
        String fullPath = FILEPATH + now + "." + ext[1];
        multipartFile.transferTo(new File(fullPath));
        Image image = new Image(userId, now, fullPath, false, LocalDateTime.now());
        imageRepository.save(image);
    }

    private boolean extValidationCheck(String ext) {
        String[] validExt = {"jpg", "jpeg", "png", "gif"};
        for (String s : validExt) {
            if (s.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    public List<Image> findAll(Long userId) {
        return imageRepository.findAllByUserId(userId);
    }
}
