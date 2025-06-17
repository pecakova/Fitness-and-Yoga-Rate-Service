package com.example.yogafitnessrating.repository;

import com.example.yogafitnessrating.model.Page;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    List<Page> findByTypeIgnoreCase(String type);
    List<Page> findByCategoryIgnoreCase(String category);
    List<Page> findByTypeIgnoreCaseAndCategoryIgnoreCase(String type, String category);
    List<Page> findByAverageRatingGreaterThanEqual(Double rating);
    
}
