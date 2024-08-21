package com.example.capstone2.Repository;

import com.example.capstone2.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    Bill findBillById(Integer id);

   int countByUserIdAndWashId(Integer userId, Integer washId);

    @Query("SELECT w.typeWashing FROM Bill b JOIN Washing w ON b.washingId = w.id WHERE b.userId = :userId GROUP BY w.typeWashing ORDER BY COUNT(w.typeWashing) DESC")
    String findMostFrequentWashTypeByUserId( Integer userId);
}

//    @Query("SELECT COUNT(b) FROM Bill b WHERE b.userId = ?1 AND b.washingId = ?2")
//    int countByUserIdAndWashingId(Integer userId, Integer washingId);
//
//
//    @Query("SELECT b FROM Bill b ORDER BY b.id ASC")
//    Bill findFirstByOrderByIdAsc();

//    List<Bill> findMostFrequentWashTypeByUserId(Integer userId,Integer washingId);
//
//


