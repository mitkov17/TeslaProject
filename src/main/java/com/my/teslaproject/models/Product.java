package com.my.teslaproject.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    @Column(name = "price")
    private int price;

    @Column(name = "path_to_img")
    private String pathToImg;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    public Product() {

    }

    public Product(String brand, String productName, int yearOfRelease, int price) {
        this.brand = brand;
        this.productName = productName;
        this.yearOfRelease = yearOfRelease;
        this.price = price;
    }
}
