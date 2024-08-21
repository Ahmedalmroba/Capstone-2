package com.example.capstone2.Repository;

import com.example.capstone2.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByWashId(Integer washId);

    Rating findByRatingId(Integer ratingId);

    List<Rating> findByUserId(Integer userId);
}