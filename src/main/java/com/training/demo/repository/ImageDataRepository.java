package com.training.demo.repository;


import com.training.demo.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Integer> {
    @Query("SELECT i FROM ImageData i WHERE i.post_id = :postId")
    List<ImageData> findByPostId(@Param("postId") int postId);
}

