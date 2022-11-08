package com.my.teslaproject.services;

import com.my.teslaproject.models.Order;
import com.my.teslaproject.models.Person;
import com.my.teslaproject.models.Product;
import com.my.teslaproject.repositories.OrdersRepository;
import com.my.teslaproject.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ProductsRepository productsRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, ProductsRepository productsRepository) {
        this.ordersRepository = ordersRepository;
        this.productsRepository = productsRepository;
    }

    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    public List<Order> findByCustomer(Optional<Person> person) {
        return ordersRepository.findByCustomer(person);
    }

    public Order findOne(int id) {
        Optional<Order> foundOrder = ordersRepository.findById(id);
        return foundOrder.orElse(null);
    }

    @Transactional
    public void save(Order order, Person person, Product product) {
        order.setCustomer(person);
        order.setProduct(product);
        order.setCreatedAt(new Date());
        ordersRepository.save(order);
    }

    @Transactional
    public void update(int id, Order updatedOrder) {
        updatedOrder.setId(id);
        ordersRepository.save(updatedOrder);
    }

    @Transactional
    public void delete(int id) {
        ordersRepository.deleteById(id);
    }

}
