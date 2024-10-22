package com.claucio.bluefood.infrastructure.web.controller;

import com.claucio.bluefood.application.ClientService;
import com.claucio.bluefood.domain.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String newClient(Model model){
        model.addAttribute("client", new Client());
        return "cliente-cadastro";
    }

    @PostMapping(path = "/client/save")
    public String save(@ModelAttribute("client") Client client){
        clientService.saveClient(client);
        return "cliente-cadastro";
    }

}
