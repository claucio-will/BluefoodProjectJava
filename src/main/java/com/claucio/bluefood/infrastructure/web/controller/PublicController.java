package com.claucio.bluefood.infrastructure.web.controller;

import com.claucio.bluefood.application.ClientService;
import com.claucio.bluefood.application.ValidationException;
import com.claucio.bluefood.domain.client.Client;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/client/new")
    public String newClient(Model model) {
        model.addAttribute("client", new Client());
        ControllerHelper.setEditMode(model, false);
        return "cliente-cadastro";
    }

    @PostMapping(path = "/client/save")
    public String save(@ModelAttribute("client") @Valid Client client, Errors errors, Model model) {
        if (!errors.hasErrors()){
            try {
                clientService.saveClient(client);
                model.addAttribute("msg", "Cliente gravado com sucesso");
            } catch (ValidationException e) {
               errors.rejectValue("email",null, e.getMessage());
            }
        }
        ControllerHelper.setEditMode(model, false);
        return "cliente-cadastro";
    }

}
