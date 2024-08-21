package com.example.capstone2.Controller;

import com.example.capstone2.Model.Rating;
import com.example.capstone2.Service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rating")
public class RatingController {
    private final RatingService ratingService;
    @GetMapping("/get")
    public ResponseEntity GetRating() {
        return ResponseEntity.status(200).body(ratingService.getRatings());
    }
    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Rating rating) {

        ratingService.addRating(rating);
        return ResponseEntity.status(200).body("rating added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id ,@Valid @RequestBody Rating rating) {


        ratingService.updateRating(id,rating);
        return ResponseEntity.status(200).body("rating updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        ratingService.deleteRating(id);
        return ResponseEntity.status(200).body("rating deleted");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Integer id) {
        List<Rating> rating= ratingService.getRatingsByWashId(id);
        return ResponseEntity.status(200).body(rating.get(0));
}}
