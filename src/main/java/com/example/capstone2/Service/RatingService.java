package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Rating;
import com.example.capstone2.Repository.RatingRepository;
import com.example.capstone2.Repository.WashingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final WashingRepository WashingRepository;

    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }
    public void addRating(Rating rating) {
        ratingRepository.save(rating);
    }
    public void updateRating(Integer id,Rating rating) {
        Rating r = ratingRepository.findById(id).get();
        if (r == null) {
            throw new ApiException("Rating not found");
        }
        r.setRating(rating.getRating());
        r.setWashId(rating.getWashId());
        r.setComments(rating.getComments());
        ratingRepository.save(r);
    }
    public void deleteRating(Integer id) {
        Rating r = ratingRepository.findByRatingId(id);
        if (r == null) {
            throw new ApiException("Rating not found");
        }

    }
    public List<Rating> getRatingsByWashId(Integer washId) {
        return ratingRepository.findByWashId(washId);
        
    }


    }