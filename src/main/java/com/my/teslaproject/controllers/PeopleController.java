package com.my.teslaproject.controllers;

import com.my.teslaproject.models.Person;
import com.my.teslaproject.services.PeopleService;
import com.my.teslaproject.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    /*@GetMapping("/{id}")
    public String personPage(Model model) {
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = person.getUsername();
        model.addAttribute("username", username);
        return "people/personPage";
    }*/

    /*@GetMapping("/personPage")
    public String personPage(Model model) {
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int idPerson = person.getId();
        model.addAttribute("person", peopleService.findOne(idPerson));
        return "people/personPage";
    }*/

    @GetMapping()
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/personPage")
    public String userPanel(Principal principal, Model model){
        Optional<Person> user = peopleService.getPersonByUsername(principal.getName());


        if (user != null) {
            model.addAttribute("user", user);
        }else {
            return "error/404";
        }

        return "people/personPage";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));

        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, @PathVariable("id") int id, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}