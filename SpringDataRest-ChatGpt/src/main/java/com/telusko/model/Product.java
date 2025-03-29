package com.telusko.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // Marks this class as a database table
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    private String name;
    private String category;
    private double price;
}
