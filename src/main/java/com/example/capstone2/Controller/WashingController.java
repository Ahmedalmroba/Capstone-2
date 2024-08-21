package com.example.capstone2.Controller;

import com.example.capstone2.Model.Washing;

import com.example.capstone2.Service.WashingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/washing")
public class WashingController {
    private final WashingService washingService;

    @GetMapping("/get")
    public ResponseEntity getWashing() {
        return ResponseEntity.status(200).body(washingService.getAllWashings());
    }

    @PostMapping("/add")
    public ResponseEntity addWashing(@Valid @RequestBody Washing washing, Errors errors) {

        washingService.addWashing(washing);
        return ResponseEntity.status(200).body("Washing added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateWashing(@PathVariable Integer id, @RequestBody Washing washing) {

        washingService.updateWashing(id, washing);
        return ResponseEntity.status(200).body("Washing updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteWashing(@PathVariable Integer id) {
        washingService.deleteWashing(id);
        return ResponseEntity.status(200).body("Washing deleted");
    }
    @GetMapping("/getWashing/{id}")
    public ResponseEntity getWashing(@PathVariable Integer id) {
        washingService.getWashById(id);
        return ResponseEntity.status(200).body(washingService.getWashById(id));
    }

    @GetMapping("status/{status}")
    public ResponseEntity searchByStatus(@PathVariable String status) {
        washingService.searchByStatus(status);
        return ResponseEntity.status(200).body(washingService.searchByStatus(status));}

    @GetMapping("/type/{typeWashing}")
    public ResponseEntity getCarWashesByType(@PathVariable String typeWashing) {
        return ResponseEntity.status(200).body( washingService.getCarWashesByType(typeWashing));
    }




}