package com.claucio.bluefood.application.service;

import com.claucio.bluefood.domain.client.Client;
import com.claucio.bluefood.domain.client.ClientRepository;

import com.claucio.bluefood.domain.restaurante.Restaurante;
import com.claucio.bluefood.domain.restaurante.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Transactional
    public void saveClient(Client client) throws ValidationException{
        if (!validateEmail(client.getEmail(), client.getId())){
            throw new ValidationException("Email já existe.");
        }

        if (client.getId() != null){ //Cliente já existente
            Client clientDB = clientRepository.findById(client.getId()).orElseThrow();
            client.setPassword(clientDB.getPassword());

        }else {
            client.encryptPassword(); //Novo cliente
        }
        clientRepository.save(client);
    }

    private boolean validateEmail(String email, Integer id){
        Restaurante restaurante = restauranteRepository.findByEmail(email);

        if (restaurante != null){
            return false;
        }

        Client client = clientRepository.findByEmail(email);
        if (client != null){
            if (id == null){
                return false;
            }
           if (!client.getId().equals(id)){
               return false;
           }
        }
        return true;
    }
}
