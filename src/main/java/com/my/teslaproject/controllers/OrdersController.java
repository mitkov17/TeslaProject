package com.my.teslaproject.controllers;

import com.my.teslaproject.services.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @DeleteMapping("/admin/orders/{id}")
    public String delete(@PathVariable("id") int id) {
        ordersService.delete(id);
        return "redirect:/admin/adminPage";
    }
}
