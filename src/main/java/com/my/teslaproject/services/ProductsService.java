package com.my.teslaproject.services;

import com.my.teslaproject.models.Product;
import com.my.teslaproject.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public List<Product> findAllWithSort(String sortBy) {
        if (Objects.equals(sortBy, "priceA")) {
            return productsRepository.findAll(Sort.by("price"));
        } else if (Objects.equals(sortBy, "priceD")) {
            return productsRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        } else {
            return productsRepository.findAll(Sort.by("id"));
        }
    }

    public List<Product> findAllByBrand(String brand) {
        return productsRepository.findByBrand(brand);
    }

    public List<Product> findByProductName(String productName) {
        return productsRepository.findByProductNameStartingWith(productName);
    }

    public Product findOne(int id) {
        Optional<Product> foundProduct = productsRepository.findById(id);
        return foundProduct.orElse(null);
    }

    @Transactional
    public void save(Product product) {
        productsRepository.save(product);
    }

    @Transactional
    public void update(int id, Product updatedProduct) {
        updatedProduct.setId(id);
        productsRepository.save(updatedProduct);
    }


    @Transactional
    public void delete(int id) {
        productsRepository.deleteById(id);
    }
}