package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Bill;
import com.example.capstone2.Model.Users;
import com.example.capstone2.Model.Washing;
import com.example.capstone2.Repository.BillRepository;
import com.example.capstone2.Repository.UsersRepository;
import com.example.capstone2.Repository.WashingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
   private final WashingRepository washingRepository;
   private final UsersRepository usersRepository;



    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
    public void addBill(Bill bill) {
        billRepository.save(bill);
    }

    public void updateBill(Integer id,Bill bill) {
       if (usersRepository.findUsersById(bill.getUserId()) == null||
      washingRepository.findByWashingId(bill.getWashingId())==null)
           if(b == null) {
               throw new ApiException("bill not found");
           }
        Bill b = billRepository.findBillById(id);

        b.setTotalPrice(bill.getTotalPrice());
        b.setUserId(bill.getUserId());
        b.setWashingId(bill.getWashingId());
        billRepository.save(b);

    }
    public void deleteBill(Integer id) {
        Bill b = billRepository.findBillById(id);
        if(b == null) {
            throw new ApiException("bill not found");

        }
        billRepository.delete(b);

    }
    public int countUserWashesByUserAndWashingId(Integer userId, Integer washingId  ) {
        int washCount = billRepository.countByUserIdAndWashingId(userId, washingId);

        if (washCount == 0) {
            throw new ApiException("No washes found for user ID");
        }

        return washCount;


    }
    public Bill createQuickWashBill(Integer userId, Integer washingId) {

        Users user = usersRepository.findUsersById(userId);


        Washing washing = washingRepository.findWashingById(washingId);
        if (user==null)
            throw new ApiException("user not found");

        double originalPrice = washing.getPrice(); // Assuming Washing has a getPrice() method
        Double quickWashPrice = originalPrice * 1.20;

        Bill bill = createBil(userId, washingId, quickWashPrice);
        return billRepository.save(bill);
    }

    private Bill createBil(Integer userId, Integer washingId, Double totalPrice) {
        Bill bill = billRepository.findFirstByOrderByIdAsc();  // Fetch an existing bill as a template
        bill.setUserId(userId);
        bill.setWashingId(washingId);
        bill.setTotalPrice(totalPrice);
        return bill;

}

    public Bill processFreeWashIfEligible(Integer userId, Integer washingId, Double washingPrice) {

        Users user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ApiException("User not found for ID: " + userId);
        }

        int washCount = billRepository.countByUserIdAndWashingId(userId,washingId);

        boolean isFree = (washCount + 1) % 5 == 0;


        Bill bill = billRepository.findFirstByOrderByIdAsc();
        if (bill == null) {

            throw new ApiException("No Bill template found. Please create a base Bill template.");
        }

        Bill finalBill = new Bill();
        finalBill.setId(bill.getId());
        finalBill.setUserId(userId);
        finalBill.setWashingId(washingId);

        if (isFree) {
            finalBill.setTotalPrice(0.0);
        } else {
            finalBill.setTotalPrice(washingPrice);
        }

        return billRepository.save(finalBill);
}
    public List<Bill>getMostFrequentWashTypeForUser(Integer userId , Integer washingId) {
        List<Bill> list =  billRepository.findMostFrequentWashTypeByUserId(userId,washingId);
        if (list == null) {
            throw new ApiException("washing not found");
        }
        return list;
    }

}

