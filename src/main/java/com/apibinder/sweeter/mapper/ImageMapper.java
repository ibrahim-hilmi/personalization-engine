package com.apibinder.sweeter.mapper;

import com.apibinder.sweeter.dto.ImageDTO;
import com.apibinder.sweeter.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDTO map(Image tag);
}
