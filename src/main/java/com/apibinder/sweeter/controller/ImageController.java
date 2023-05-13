package com.apibinder.sweeter.controller;

import com.apibinder.sweeter.model.Image;
import com.apibinder.sweeter.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<Image> getImage(){
        return new ResponseEntity<>(imageService.getNextImage(), HttpStatus.OK);
    }
}
