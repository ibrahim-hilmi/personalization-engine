package com.apibinder.sweeter.controller;

import com.apibinder.sweeter.service.ImageService;
import lombok.RequiredArgsConstructor;
import com.apibinder.sweeter.model.Image;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{uid}")
    public List<Image> getImage(@PathVariable("uid") String uid){
        return imageService.getNextImage(uid);
    }
}
