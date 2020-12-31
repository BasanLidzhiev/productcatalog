package ru.lidzhiev.productcatalog.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int price;
}
