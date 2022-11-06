package com.my.teslaproject.controllers;

import com.my.teslaproject.models.Contact;
import com.my.teslaproject.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    private final MailService mailService;

    @Autowired
    public IndexController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping()
    public String getInd(ModelMap modelMap) {
        modelMap.put("contact", new Contact());
        return "index";
    }

    @PostMapping("/send")
    public String send(@ModelAttribute("contact") Contact contact, ModelMap modelMap) throws Exception {

        try {
            String content = "Имя: " + contact.getName();
            content += "<br>Фамилия: " + contact.getSurname();
            content += "<br>Телефон: " + contact.getPhone();
            content += "<br>Модель электромобиля: " + contact.getNameCar();
            content += "<br><br>Описание проблемы: <br>" + contact.getInfo();

            mailService.send(contact.getEmail(), "maxitan1701@gmail.com", "Консультация Tesla", content);

            modelMap.put("msg", "Done!");
        } catch (Exception e) {
            modelMap.put("msg", e.getMessage());
        }

        return "redirect:/index";
    }

}
