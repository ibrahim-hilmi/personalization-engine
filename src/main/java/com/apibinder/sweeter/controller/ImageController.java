package com.apibinder.sweeter.controller;

import com.apibinder.sweeter.dto.ImageDTO;
import com.apibinder.sweeter.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;


    @GetMapping("/detail")
    public ResponseEntity<ImageDTO> getImageDetail() {
        return new ResponseEntity<>(imageService.getNextImage(), HttpStatus.OK);
    }

    @GetMapping(value = "{path}",produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getImage(@PathVariable("path") String path) {
        return new ClassPathResource("images/" + path);
    }
}
