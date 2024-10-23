package com.claucio.bluefood.application;

import com.claucio.bluefood.domain.client.Client;
import com.claucio.bluefood.domain.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client) throws ValidationException{
        if (!validateEmail(client.getEmail(), client.getId())){
            throw new ValidationException("Email já existe.");
        }
        clientRepository.save(client);
    }

    private boolean validateEmail(String email, Integer id){
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
