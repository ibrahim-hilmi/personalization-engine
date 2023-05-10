package com.apibinder.sweeter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "image_show_log")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageShowLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userId;
    private Integer imageId;
    private Date createdDate;

}
