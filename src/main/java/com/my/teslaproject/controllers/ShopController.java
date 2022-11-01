package com.my.teslaproject.controllers;

import com.my.teslaproject.models.Person;
import com.my.teslaproject.services.PeopleService;
import com.my.teslaproject.services.ProductsService;
import com.my.teslaproject.util.PersonValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ProductsService productsService;

    public ShopController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public String shopPage(Model model, @RequestParam(value = "sort_by", required = false) String sortBy,
                           @RequestParam(value = "find_by_brand", required = false) String brand,
                           @RequestParam(value = "find_by_product_name", required = false) String productName) {

        if (brand != null) model.addAttribute("products", productsService.findAllByBrand(brand));

        else model.addAttribute("products", productsService.findAllWithSort(sortBy));

        return "shop";
    }

    @PostMapping ("/search")
    public String searchCar(Model model, @RequestParam(value = "query") String query) {
        model.addAttribute("products", productsService.findByProductName(query));
        return "shop";
    }
}