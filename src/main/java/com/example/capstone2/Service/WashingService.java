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
public class WashingService {
    private final WashingRepository washingRepository;
   // private final UsersRepository usersRepository;

    public List<Washing> getAllWashings() {
        return washingRepository.findAll();
    }

    public void addWashing(Washing washing) {
        washingRepository.save(washing);

    }

    public void updateWashing(Integer id, Washing washing) {

        Washing w = washingRepository.findWashingById(id);
        if (w == null) {
            throw new ApiException("washing not found");
        }
        w.setTypeWashing(washing.getTypeWashing());
        w.setTypeCar(washing.getTypeCar());
        w.setPrice(washing.getPrice());
        washingRepository.save(w);
    }

    public void deleteWashing(Integer id) {
        Washing w = washingRepository.findWashingById(id);
        if (w == null) {
            throw new ApiException("washing not found");
        }
        washingRepository.delete(w);
    }
    public Washing getWashById(Integer id) {
        Washing w = washingRepository.findWashingById(id);
        if (w == null) {
            throw new ApiException("washing not found");
        }
        return w;
    }

    public List<Washing> searchByStatus(String status) {
        List<Washing> Washes = washingRepository.searchByStatus(status);
        if (Washes == null) {
            throw new ApiException("washing not found");
        }
        return Washes;
    }

    public List<Washing> getCarWashesByType(String typeWashing) {
        List<Washing> Washes = washingRepository.findByTypeWashing(typeWashing);
        if (Washes == null) {
            throw new ApiException("washing not found");
        }
        return Washes;
    }



}

