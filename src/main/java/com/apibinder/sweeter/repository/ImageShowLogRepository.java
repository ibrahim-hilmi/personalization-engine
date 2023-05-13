package com.apibinder.sweeter.repository;

import com.apibinder.sweeter.model.ImageShowLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageShowLogRepository extends JpaRepository<ImageShowLog, Integer> {
}
