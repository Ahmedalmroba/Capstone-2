package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Users;
import com.example.capstone2.Model.Washing;
import com.example.capstone2.Repository.UsersRepository;
import com.example.capstone2.Repository.WashingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
   private final WashingRepository washingRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public void addUser(Users user) {
        usersRepository.save(user);
    }

    public void updateUser(Integer id,Users user) {
        Users u = usersRepository.findUsersById(id);
        if (u == null) {
            throw new ApiException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setPhoneNumber(user.getPhoneNumber());
        usersRepository.save(u);
    }
    public void deleteUser(Integer id) {
        Users u = usersRepository.findUsersById(id);
        if (u == null) {
            throw new ApiException("User not found");
        }
        usersRepository.delete(u);
    }

//    public List<Washing> getUserWashHistory(Integer userId) {
//
//        Users user = usersRepository.findUsersById(userId);
//        if (user == null) {
//            throw new ApiException("User not found");
//        }
//
//
//        return washingRepository.findByUserId(userId);
//
//
//
//
}










