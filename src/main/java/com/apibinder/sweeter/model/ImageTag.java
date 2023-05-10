package com.apibinder.sweeter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "image_tag")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer imageId;
    private String tagKey;
    private String tagValue;
}
