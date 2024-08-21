package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer washId;
    @NotEmpty(message = "must not be empty")
    @Column(columnDefinition = "varchar(10) not null unique ")
    private Integer rating;
    @NotEmpty(message = "must not be empty")
    @Column(columnDefinition = "varchar(15) not null unique ")
    private String comments;
}

