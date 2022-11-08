package com.my.teslaproject.controllers;

import com.my.teslaproject.models.Order;
import com.my.teslaproject.models.Person;
import com.my.teslaproject.models.Product;
import com.my.teslaproject.security.PersonDetails;
import com.my.teslaproject.services.OrdersService;
import com.my.teslaproject.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                           @RequestParam(value = "find_by_brand", required = false) String brand) {

        if (brand != null) model.addAttribute("products", productsService.findAllByBrand(brand));

        else model.addAttribute("products", productsService.findAllWithSort(sortBy));

        model.addAttribute("productOrder", new Product());

        return "shop/shop";
    }

    @PostMapping ("/search")
    public String searchCar(Model model, @RequestParam(value = "query") String query) {
        model.addAttribute("products", productsService.findByProductName(query));
        return "shop/shop";
    }

    @GetMapping("/makeOrder/{id}")
    public String show(Model model, @PathVariable("id") int id) {

        model.addAttribute("product", productsService.findOne(id));

        return "shop/shoppingCart";
    }

    @Transactional
    @PostMapping("/makeOrder/{id}")
    public String addProduct(@ModelAttribute("order") Order order, @PathVariable("id") int id) {
        PersonDetails personDetails = (PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person = personDetails.getPerson();

        Product product = productsService.findOne(id);

        ordersService.save(order, person, product);

        return "redirect:/shop";
    }
}