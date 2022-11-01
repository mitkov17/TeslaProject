package com.my.teslaproject.repositories;

import com.my.teslaproject.models.Person;
import com.my.teslaproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    List<Product> findByBrand(String brand);
}
