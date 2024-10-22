package com.claucio.bluefood.application;

import com.claucio.bluefood.domain.client.Client;
import com.claucio.bluefood.domain.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client){
        clientRepository.save(client);
    }



}
