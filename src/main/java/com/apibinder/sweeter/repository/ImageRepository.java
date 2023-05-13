package com.apibinder.sweeter.repository;

import com.apibinder.sweeter.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository  extends JpaRepository<Image, Integer> {

    @Query(value = "SELECT * \n" +
           "FROM image \n" +
           "WHERE id NOT IN (SELECT image_id FROM image_show_log WHERE user_id = :uid)\n" +
           "AND id IN (SELECT image_id FROM image_tag WHERE tag_key = :tagKey AND tag_value = :tagValue) LIMIT 1", nativeQuery = true)
    Image findNextImage(@Param("uid") String uid, @Param("tagKey") String tagKey, @Param("tagValue") String tagValue);

}
