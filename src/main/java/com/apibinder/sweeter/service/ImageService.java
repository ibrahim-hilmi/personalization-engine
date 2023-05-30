package com.apibinder.sweeter.service;

import com.apibinder.sweeter.client.TagClient;
import com.apibinder.sweeter.dto.ImageDTO;
import com.apibinder.sweeter.mapper.ImageMapper;
import com.apibinder.sweeter.model.Image;
import com.apibinder.sweeter.model.ImageShowLog;
import com.apibinder.sweeter.model.ImageTag;
import com.apibinder.sweeter.repository.ImageRepository;
import com.apibinder.sweeter.repository.ImageShowLogRepository;
import com.apibinder.sweeter.statics.UserConst;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageShowLogRepository imageShowLogRepository;
    private final ImageMapper imageMapper;
    private int totalTagCountForUser = 0;
    private static int showedImageTagCount = 0;
    private static String lastShowedImageTagValue;

    @Transactional
    public ImageDTO getNextImage(){
        Map<String, Integer> counts = getCountWithSum();
        Image nextImage;

        if (counts.size() != 0
                && totalTagCountForUser >= UserConst.LIMIT
                && showedImageTagCount < UserConst.LIMIT){

            String selectedValue = getSelectedValue(counts);
            nextImage = imageRepository.findNextImage(UserConst.USER_ID, UserConst.PREFERED_TAG_KEY, selectedValue);
            checkIfTagsAreSame(selectedValue);
            System.out.println("GETTING SELECTED VALUE: " + selectedValue);
        } else {
            if(showedImageTagCount < UserConst.LIMIT){
                nextImage = imageRepository.selectRandomImage(UserConst.USER_ID);
                System.out.println("GETTING RANDOM count size->" + counts.size() + " total tag count-> " + totalTagCountForUser + " showedImageTagCount-> " + showedImageTagCount);

            } else {
                nextImage = imageRepository.selectRandomImageNotIn(UserConst.USER_ID, UserConst.PREFERED_TAG_KEY, lastShowedImageTagValue);
                System.out.println("GETTING RANDOM NOT IN count size->" + counts.size() + " total tag count-> " + totalTagCountForUser + " showedImageTagCount-> " + showedImageTagCount);
            }

            showedImageTagCount = 0;
        }

        if (nextImage == null) {
            System.out.println("Image Resource Ended, Getting Random Image!");
            nextImage = imageRepository.selectRandomImage(UserConst.USER_ID);
        }
        

        lastShowedImageTagValue = nextImage.getImagePreferedTagValue();

        saveImageShowLog(nextImage);

        return imageMapper.map(nextImage);
    }

    private void checkIfTagsAreSame(String selectedValue) {
        if (Objects.equals(selectedValue, lastShowedImageTagValue)) {
            showedImageTagCount += 1;
        }

        else {
            showedImageTagCount = 0;
        }
    }

    private void saveImageShowLog(Image nextImage) {
        ImageShowLog imageShowLog = new ImageShowLog();
        imageShowLog.setUserId(UserConst.USER_ID);
        imageShowLog.setImageId(nextImage.getId());
        imageShowLogRepository.save(imageShowLog);
    }

    private String getSelectedValue(Map<String, Integer> counts) {
        Random random = new Random();
        int randomNumber = random.nextInt(totalTagCountForUser);

        for(Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (randomNumber <= entry.getValue())
                return entry.getKey();
        }
        return null;
    }

    public Map<String, Integer> getCountWithSum(){
        TagClient tagClient = new TagClient();
        Map<String, Integer> counts = tagClient.getTagCounts(UserConst.USER_ID, UserConst.PREFERED_TAG_KEY, UserConst.LIMIT);
        totalTagCountForUser = 0;
        for(Map.Entry<String, Integer> entry : counts.entrySet()) {
            totalTagCountForUser += entry.getValue();
            counts.put(entry.getKey(), totalTagCountForUser);
        }
        return counts;
    }


}
