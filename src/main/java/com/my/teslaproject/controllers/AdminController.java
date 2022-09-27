package com.my.teslaproject.controllers;

import com.my.teslaproject.models.Person;
import com.my.teslaproject.models.Product;
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
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final ProductsService productsService;

    @Autowired
    public AdminController(PeopleService peopleService, PersonValidator personValidator, RegistrationService registrationService, ProductsService productsService) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.productsService = productsService;
    }

    @GetMapping("/adminPage")
    public String adminIndex(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("products", productsService.findAll());
        return "admin/adminPage";
    }

    @GetMapping("/users/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));

        return "admin/showUser";
    }

    @GetMapping("/newUser")
    public String adminRegistration(@ModelAttribute("person") Person person) {
        return "admin/newUser";
    }

    @PostMapping("/newUser")
    public String adminRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()) {
            return "/admin/newUser";
        }

        registrationService.register(person);

        return "redirect:/admin/adminPage";
    }

    @GetMapping("/{id}/editUser")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));

        return "admin/editUser";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, @PathVariable("id") int id, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/admin/editUser";
        }

        peopleService.update(id, person);
        return "redirect:/admin/adminPage";
    }

    /*@DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/admin/adminPage";
    }*/


    //////////Products////////////

    @GetMapping("/products/{id}")
    public String showProduct(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", productsService.findOne(id));

        return "admin/showProduct";
    }

    @GetMapping("/newProduct")
    public String addProduct(@ModelAttribute("product") Product product) {
        return "admin/newProduct";
    }

    @PostMapping("/newProduct")
    //@PostMapping()
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "/admin/newProduct";
        }

        productsService.save(product);

        return "redirect:/admin/adminPage";
    }

    @GetMapping("/{id}/editProduct")
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
