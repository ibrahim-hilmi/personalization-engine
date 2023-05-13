package com.apibinder.sweeter.service;

import com.apibinder.sweeter.client.TagClient;
import com.apibinder.sweeter.model.Image;
import com.apibinder.sweeter.model.ImageShowLog;
import com.apibinder.sweeter.repository.ImageRepository;
import com.apibinder.sweeter.repository.ImageShowLogRepository;
import com.apibinder.sweeter.statics.UserConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageShowLogRepository imageShowLogRepository;
    private int valueSum = 0;

    public Image getNextImage(){
        Map<String, Integer> percents = getPercentsWithSum();
        // TODO: 13.05.2023 handle if there is no percent 
        String selectedValue = getSelectedValue(percents);

        Image nextImage = imageRepository.findNextImage(UserConst.USER_ID, UserConst.PREFERED_TAG_KEY, selectedValue);
        saveImageShowLog(nextImage);

        return nextImage;
    }

    private void saveImageShowLog(Image nextImage) {
        ImageShowLog imageShowLog = new ImageShowLog();
        imageShowLog.setUserId(UserConst.USER_ID);
        imageShowLog.setImageId(nextImage.getId());
        imageShowLogRepository.save(imageShowLog);
    }

    private String getSelectedValue(Map<String, Integer> percents) {
        Random random = new Random();
        int randomNumber = random.nextInt(valueSum);

        for(Map.Entry<String, Integer> entry : percents.entrySet()) {
            if (randomNumber < entry.getValue())
                return entry.getKey();
        }
        return null;
    }

    public Map<String, Integer> getPercentsWithSum(){
        TagClient tagClient = new TagClient();
        Map<String, Integer> percents = tagClient.getTagPercents(UserConst.USER_ID, UserConst.PREFERED_TAG_KEY);

        for(Map.Entry<String, Integer> entry : percents.entrySet()) {
            valueSum += entry.getValue() + UserConst.ADD_PERCENT;
            percents.put(entry.getKey(), valueSum);
        }
        return percents;
    }
}
