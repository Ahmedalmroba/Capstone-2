package com.example.capstone2.Repository;

import com.example.capstone2.Model.Users;
import com.example.capstone2.Model.Washing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WashingRepository extends JpaRepository<Washing, Integer> {
    Washing findWashingById(Integer washingId);
    List<Washing> searchByStatus(String status);

    @Query("SELECT c FROM Washing c WHERE c.typeWashing = ?1")
    List<Washing> findByTypeWashing(String typeWashing);










}
