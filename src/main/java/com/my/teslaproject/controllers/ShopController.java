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

    /*@GetMapping()
    public String shopPage(Model model, @RequestParam(value = "sort_by_price", required = false) boolean sortByPrice,
                           @RequestParam(value = "sort_by_id", required = false) boolean sortById) {
        if (sortByPrice && !sortById) {
            model.addAttribute("products", productsService.findAllWithSortByPrice(true));
        } else if (!sortByPrice && !sortById) {
            model.addAttribute("products", productsService.findAllWithSortByPrice(false));
        } else if (sortById) {
            model.addAttribute("products", productsService.findAllWithSortById());
        }
        return "shop";
    }*/

    @GetMapping()
    public String shopPage(Model model, @RequestParam(value = "sort_by", required = false) String sortBy) {
        if (Objects.equals(sortBy, "priceA")) {
            model.addAttribute("products", productsService.findAllWithSort("priceA"));
        } else if (Objects.equals(sortBy, "priceD")) {
            model.addAttribute("products", productsService.findAllWithSort("priceD"));
        } else {
            model.addAttribute("products", productsService.findAllWithSort("idd"));
        }
        return "shop";
    }
}