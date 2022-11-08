package com.my.teslaproject.controllers;

import com.my.teslaproject.models.Person;
import com.my.teslaproject.services.OrdersService;
import com.my.teslaproject.services.PeopleService;
import com.my.teslaproject.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PersonValidator personValidator;
    private final OrdersService ordersService;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator, OrdersService ordersService) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
        this.ordersService = ordersService;
    }

    @GetMapping()
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/personPage")
    public String userPanel(Principal principal, Model model){
        Optional<Person> user = peopleService.getPersonByUsername(principal.getName());

        model.addAttribute("orders", ordersService.findByCustomer(user));

        if (user.isPresent()) {
            model.addAttribute("user", user);
        } else {
            return "error/404";
        }

        return "people/personPage";
    }
}