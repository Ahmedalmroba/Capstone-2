package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Washing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    //@Column(columnDefinition ="varchar(5)check(typeCar='car' or typeCar='motorcycle' or typeCar='truck')")
    @NotEmpty(message = "name should be not Empty")
    @Column(columnDefinition = "varchar(20) not null  ")
    @Pattern(regexp = "car|motorcycle|truck")
    private String typeCar;
    //@Column(columnDefinition ="varchar(5)check(typeWashing='normal' or typeWashing='polish' or typeWashing='steam')")
    @NotEmpty(message = "name should be not Empty")
   @Column(columnDefinition = "varchar(20) not null  ")
    @Pattern(regexp = "normal|polish|steam")
    private String typeWashing;
    @NotNull(message = "must not be null")
    @Column(columnDefinition = " int not null ")
    @Positive
    private int price;
    @NotEmpty(message = "name should be not Empty")

    @Column(columnDefinition ="varchar(5)check(status='Not Started' or status='in Progress' or status='Completed')")
    @Pattern(regexp = "Not Started|in Progress|Completed")
    private String status;

    private String dateWash;



}
