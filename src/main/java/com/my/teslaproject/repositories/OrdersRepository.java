package com.my.teslaproject.repositories;

import com.my.teslaproject.models.Order;
import com.my.teslaproject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Optional<Person> person);
}
