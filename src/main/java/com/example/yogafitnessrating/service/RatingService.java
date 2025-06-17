package com.example.yogafitnessrating.service;

import com.example.yogafitnessrating.model.Page;
import com.example.yogafitnessrating.model.Rating;
import com.example.yogafitnessrating.repository.PageRepository;
import com.example.yogafitnessrating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final PageRepository pageRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, PageRepository pageRepository) {
        this.ratingRepository = ratingRepository;
        this.pageRepository = pageRepository;
    }

    public String addRating(Long pageId, int score) {
        Page page = pageRepository.findById(pageId).orElse(null);
        if (page == null) {
            return "Page not found";
        }

        if (score < 1 || score > 10) {
            return "Rating must be between 1 and 10";
        }

        Rating rating = new Rating();
        rating.setPage(page);
        rating.setScore(score);
        ratingRepository.save(rating);

        page.addRating(score);

        pageRepository.save(page);

        return "Rating added successfully";
    }
}
