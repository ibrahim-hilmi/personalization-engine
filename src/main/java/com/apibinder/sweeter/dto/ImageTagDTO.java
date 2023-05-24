package com.apibinder.sweeter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageTagDTO {
        private Integer id;
        private Integer imageId;
        private String tagKey;
        private String tagValue;
}
