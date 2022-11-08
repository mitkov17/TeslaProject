package com.my.teslaproject.controllers;

import com.my.teslaproject.models.Person;
import com.my.teslaproject.models.Product;
import com.my.teslaproject.services.OrdersService;
import com.my.teslaproject.services.PeopleService;
import com.my.teslaproject.services.ProductsService;
import com.my.teslaproject.services.RegistrationService;
import com.my.teslaproject.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PeopleService peopleService;
    private final ProductsService productsService;
    private final OrdersService ordersService;

    @Autowired
    public AdminController(PeopleService peopleService, ProductsService productsService, OrdersService ordersService) {
        this.peopleService = peopleService;
        this.productsService = productsService;
        this.ordersService = ordersService;
    }

    @GetMapping("/adminPage")
    public String adminIndex(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("products", productsService.findAll());
        model.addAttribute("orders", ordersService.findAll());
        return "admin/adminPage";
    }

    @GetMapping("/users/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));

        return "admin/showUser";
    }

    //////////Products////////////

    @GetMapping("/newProduct")
    public String addProduct(@ModelAttribute("product") Product product) {
        return "admin/newProduct";
    }

    @PostMapping("/newProduct")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "/admin/newProduct";
        }

        productsService.save(product);

        return "redirect:/admin/adminPage";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productsService.findOne(id));

        return "admin/editProduct";
    }

    @PatchMapping("/products/{id}")
    public String update(@ModelAttribute("product") @Valid Product product, @PathVariable("id") int id, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/admin/editProduct";
        }

        productsService.update(id, product);
        return "redirect:/admin/adminPage";
    }

    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id") int id) {
        productsService.delete(id);
        return "redirect:/admin/adminPage";
    }

}
