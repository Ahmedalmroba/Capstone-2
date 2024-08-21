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
        Bill b = billRepository.findBillById(id);
        if (b == null) {
            throw new ApiException("washing not found");
        }


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


    public Bill washVehicle(Integer userId, Integer washingId) {
        // Check if the user exists
        Users user = usersRepository.findUsersById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }

        // Check if the washing service exists
        Washing washing = washingRepository.findWashingById(washingId);
        if (washing == null) {
            throw new ApiException("Washing service not found");
        }

        Bill bill = new Bill();
        bill.setUserId(userId);
        bill.setWashingId(washingId);
        bill.setTotalPrice(washing.getPrice());

        return billRepository.save(bill);


    }
    public int countUserWashes(Integer userId, Integer washingId) {
        return billRepository.countByUserIdAndWashId(userId, washingId);
    }

    public Bill quickWash(Integer userId, Integer washingId) {
        Users user = usersRepository.findUsersById(userId);
                if (user == null) {
            throw new ApiException("Washing service not found");
        }
        Washing wash = washingRepository.findWashingById(washingId);
        if (user == null) {
            throw new ApiException("Washing service not found");
        }
        Bill bill = new Bill();
        bill.setUserId(user.getId());
        bill.setWashingId(washingId);
        bill.setTotalPrice(wash.getPrice() * 1.20);

        return billRepository.save(bill);

}
    public String getMostFrequentWashType(Integer userId) {
        return billRepository.findMostFrequentWashTypeByUserId(userId);

}}



//    public int countUserWashesByUserAndWashingId(Integer userId, Integer washingId  ) {
//        int washCount = billRepository.countByUserIdAndWashingId(userId, washingId);
//
//        if (washCount == 0) {
//            throw new ApiException("No washes found for user ID");
//        }
//
//        return washCount;
//
//
//    }
//    public Bill createQuickWashBill(Integer userId, Integer washingId) {
//
//        Users user = usersRepository.findUsersById(userId);
//
//
//        Washing washing = washingRepository.findWashingById(washingId);
//        if (user==null)
//            throw new ApiException("user not found");
//
//        double originalPrice = washing.getPrice();
//        Double quickWashPrice = originalPrice * 1.20;
//
//        Bill bill = createBil(userId, washingId, quickWashPrice);
//        return billRepository.save(bill);
//    }
//
//    private Bill createBil(Integer userId, Integer washingId, Double totalPrice) {
//        Bill bill = billRepository.findFirstByOrderByIdAsc();
//        bill.setUserId(userId);
//        bill.setWashingId(washingId);
//        bill.setTotalPrice(totalPrice);
//        return bill;
//
//}
//
//    public Bill processFreeWashIfEligible(Integer userId, Integer washingId, Double washingPrice) {
//
//        Users user = usersRepository.findById(userId).orElse(null);
//        if (user == null) {
//            throw new ApiException("User not found for ID: " + userId);
//        }
//
//        int washCount = billRepository.countByUserIdAndWashingId(userId,washingId);
//
//        boolean isFree = (washCount + 1) % 5 == 0;
//
//
//        Bill bill = billRepository.findFirstByOrderByIdAsc();
//        if (bill == null) {
//
//            throw new ApiException("No Bill template found. Please create a base Bill template.");
//        }
//
//        Bill finalBill = new Bill();
//        finalBill.setId(bill.getId());
//        finalBill.setUserId(userId);
//        finalBill.setWashingId(washingId);
//
//        if (isFree) {
//            finalBill.setTotalPrice(0.0);
//        } else {
//            finalBill.setTotalPrice(washingPrice);
//        }
//
//        return billRepository.save(finalBill);
//}
//    public List<Bill>getMostFrequentWashTypeForUser(Integer userId , Integer washingId) {
//        List<Bill> list =  billRepository.findMostFrequentWashTypeByUserId(userId,washingId);
//        if (list == null) {
//            throw new ApiException("washing not found");
//        }
//        return list;
//    }



