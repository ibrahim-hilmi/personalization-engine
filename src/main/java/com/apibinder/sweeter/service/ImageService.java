package com.apibinder.sweeter.service;

import com.apibinder.sweeter.client.TagClient;
import com.apibinder.sweeter.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import com.apibinder.sweeter.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<Image> getNextImage(String uid){

        TagClient tagClient = new TagClient();
        tagClient.getTagCounts("uid1", "key1");

        // TODO: 10.05.2023
        return imageRepository.findNextImage(uid, "cinsiyet", "erkek");
    }
}
