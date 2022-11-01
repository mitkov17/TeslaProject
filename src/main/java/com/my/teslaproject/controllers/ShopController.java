package com.my.teslaproject.controllers;

import com.my.teslaproject.models.Person;
import com.my.teslaproject.services.PeopleService;
import com.my.teslaproject.services.ProductsService;
import com.my.teslaproject.util.PersonValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                           @RequestParam(value = "find_by_brand", required = false) String brand) {

        if (brand != null) model.addAttribute("products", productsService.findAllByBrand(brand));

        else model.addAttribute("products", productsService.findAllWithSort(sortBy));

        return "shop";
    }
}