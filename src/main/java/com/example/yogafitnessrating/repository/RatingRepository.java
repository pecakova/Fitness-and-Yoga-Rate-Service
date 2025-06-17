package com.example.yogafitnessrating.repository;

import com.example.yogafitnessrating.model.Page;
import com.example.yogafitnessrating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByPage(Page page);
}
