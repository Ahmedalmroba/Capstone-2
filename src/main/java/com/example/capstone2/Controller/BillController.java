package com.example.capstone2.Controller;

import com.example.capstone2.Model.Bill;
import com.example.capstone2.Service.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bill")
public class BillController {
    private final BillService billService;


    @GetMapping("/get")
    public ResponseEntity getBills() {
        return ResponseEntity.status(200).body(billService.getAllBills());
    }

    @PostMapping("/add")
    public ResponseEntity addBill(@Valid @RequestBody Bill bill, Errors errors) {
        if (errors.hasErrors()) {
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);
        }
        billService.addBill(bill);
        return ResponseEntity.status(200).body("bill added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBill(@PathVariable Integer id, @Valid @RequestBody Bill bill, Errors errors) {
        if (errors.hasErrors()) {
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);
        }
        billService.updateBill(id, bill);
        return ResponseEntity.status(200).body("bill updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBill(@PathVariable Integer id) {
        billService.deleteBill(id);
        return ResponseEntity.status(200).body("bill deleted");
    }

    @GetMapping("/user/wash-count/{userId}/{washingId}")
    public ResponseEntity countUserWashesByWashingId(@PathVariable Integer userId, @PathVariable Integer washingId) {

        int washCount = billService.countUserWashesByUserAndWashingId(userId, washingId);
        return ResponseEntity.status(200).body(washCount);

}
    @PostMapping("/quick-wash/{userId}/{washingId}")
    public ResponseEntity<Bill> createQuickWashBill(@PathVariable Integer userId, @PathVariable Integer washingId) {
        Bill bill = billService.createQuickWashBill(userId, washingId);
        return ResponseEntity.status(200).body(bill);

}
    @PostMapping("/process-free-wash/{userId}/{washingId}")
    public ResponseEntity<Bill> processFreeWash(@PathVariable Integer userId, @PathVariable Integer washingId ) {
        Bill bill = billService.processFreeWashIfEligible(userId, washingId);
        return ResponseEntity.status(200).body(bill);

    }

    @GetMapping("/users/{userId}/most-frequent-wash-type")
    public ResponseEntity getMostFrequentWashTypeForUser(@PathVariable Integer userId,Integer washId) {
        billService.getMostFrequentWashTypeForUser(userId,washId);
        return ResponseEntity.status(200).body("most frequent wash type found");
    }


}
