package com.apibinder.sweeter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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
        return getImageTags().stream()
                .map(tag -> tag.getTagKey() + "=" + tag.getTagValue())
                .collect(Collectors.joining("&"));
    }
}

