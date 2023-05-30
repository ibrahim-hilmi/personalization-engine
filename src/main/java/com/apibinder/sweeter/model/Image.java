package com.apibinder.sweeter.model;


import com.apibinder.sweeter.statics.UserConst;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String path;
    private String title;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImageTag> imageTags;

    public String getImagePreferedTagValue() {
        return getImageTags().stream()
                .filter(imageTag -> UserConst.PREFERED_TAG_KEY.equals(imageTag.getTagKey()))
                .findFirst()
                .map(ImageTag::getTagValue) // Extract the tagKey value from Optional<ImageTag>
                .orElse(null); // Default value if no matching tagKey found;
    }
}
