package com.my.teslaproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class IndexController {

    @GetMapping("/index")
    public String getInd() {
        return "index";
    }

    /*@GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }*/
}
