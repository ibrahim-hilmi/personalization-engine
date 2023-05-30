package com.apibinder.sweeter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static com.apibinder.sweeter.statics.UserConst.USER_ID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageDTO {
    private Integer id;
    private String path;
    private String title;
    private List<ImageTagDTO> imageTags;

    public String getTagsText(){
        String tags = getImageTags().stream()
                .map(tag -> tag.getTagKey() + "=" + tag.getTagValue())
                .collect(Collectors.joining("&"));

        tags += "&user_id=" + USER_ID;
        return tags;
    }
}

