package com.example.capstone2.Repository;

import com.example.capstone2.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    Bill findBillById(Integer id);

    @Query("SELECT COUNT(b) FROM Bill b WHERE b.userId = ?1 AND b.washingId = ?2")
    int countByUserIdAndWashingId(Integer userId, Integer washingId);

    // Custom query to find the first bill, used as a template
    @Query("SELECT b FROM Bill b ORDER BY b.id ASC")
    Bill findFirstByOrderByIdAsc();

    List<Bill> findMostFrequentWashTypeByUserId(Integer userId,Integer washingId);



}
