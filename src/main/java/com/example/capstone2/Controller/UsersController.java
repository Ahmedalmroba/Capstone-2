package com.example.capstone2.Controller;

import com.example.capstone2.Model.Users;
import com.example.capstone2.Model.Washing;
import com.example.capstone2.Service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/get")
    public ResponseEntity getUsers() {
        return ResponseEntity.status(200).body(usersService.getAllUsers());

    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody Users users) {

        usersService.addUser(users);
        return ResponseEntity.status(200).body("User added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody Users users) {

        usersService.updateUser(id, users);
        return ResponseEntity.status(200).body("User updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        usersService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }

//    @GetMapping("/{userId}/wash-history")
//    public ResponseEntity getUserWashHistory(@PathVariable Integer userId) {
//        List<Washing> washHistory = usersService.getUserWashHistory(userId);
//        return ResponseEntity.status(200).body(washHistory);


}