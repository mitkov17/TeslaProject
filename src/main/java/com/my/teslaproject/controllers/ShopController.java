package com.my.teslaproject.controllers;

import com.my.teslaproject.models.Order;
import com.my.teslaproject.models.Person;
import com.my.teslaproject.models.Product;
import com.my.teslaproject.security.PersonDetails;
import com.my.teslaproject.services.OrdersService;
import com.my.teslaproject.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ProductsService productsService;
    private final OrdersService ordersService;

    @Autowired
    public ShopController(ProductsService productsService, OrdersService ordersService) {
        this.productsService = productsService;
        this.ordersService = ordersService;
    }

    @GetMapping()
    public String shopPage(Model model, @RequestParam(value = "sort_by", required = false) String sortBy,
                           @RequestParam(value = "find_by_brand", required = false) String brand,
                           @RequestParam(value = "find_by_product_name", required = false) String productName) {

        if (brand != null) model.addAttribute("products", productsService.findAllByBrand(brand));

        else model.addAttribute("products", productsService.findAllWithSort(sortBy));

        model.addAttribute("productOrder", new Product());

        return "shop";
    }

    @PostMapping ("/search")
    public String searchCar(Model model, @RequestParam(value = "query") String query) {
        model.addAttribute("products", productsService.findByProductName(query));
        return "shop";
    }

    /*@PostMapping("/order")
    public String makeOrder(@ModelAttribute("order") @Valid Order order,
                            @ModelAttribute("product") @Valid Product product,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "shop";

        //Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Authentication authentication = authenticationFacade.getAuthentication();
        Person person = (Person) authentication;

        ordersService.save(order, product, person);

        return "redirect:/shop";
    }*/

    @Transactional
    @PostMapping("/order")
    public String addProduct(@ModelAttribute("order") Order order,
                             @ModelAttribute("productToOrder") Product product) {
        PersonDetails personDetails = (PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person = personDetails.getPerson();

        ordersService.save(order, person, product);

        return "redirect:/shop";
    }

    /*@PostMapping("/order")
    public String makeOrder(@ModelAttribute("order") @Valid Order order,
                            @ModelAttribute("product") @Valid Product product,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "shop";

        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ordersService.save(order, product, person);

        return "redirect:/shop";
    }*/

}