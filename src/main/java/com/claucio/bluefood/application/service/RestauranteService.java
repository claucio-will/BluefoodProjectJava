package com.claucio.bluefood.application.service;

import com.claucio.bluefood.domain.client.Client;
import com.claucio.bluefood.domain.client.ClientRepository;
import com.claucio.bluefood.domain.restaurante.Restaurante;
import com.claucio.bluefood.domain.restaurante.RestauranteRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public void saveRestaurante(Restaurante restaurante) throws ValidationException {
        if (!validateEmail(restaurante.getEmail(), restaurante.getId())) {
            throw new ValidationException("Email já existe.");
        }
        if (restaurante.getId() != null){ //Cliente já existente
            Restaurante restauranteDB = restauranteRepository.findById(restaurante.getId()).orElseThrow();
            restaurante.setPassword(restauranteDB.getPassword());

        }else { //Novo cliente
            restaurante.encryptPassword();
            restaurante = restauranteRepository.save(restaurante);
            restaurante.setLogotipoFileName();
            imageService.uploadLogotipo(restaurante.getLogotipoFile(), restaurante.getLogotipo());

        }

    }

    private boolean validateEmail(String email, Integer id) {
        Client client = clientRepository.findByEmail(email);

        if (client != null){
            return false;
        }
        Restaurante restaurante = restauranteRepository.findByEmail(email);
        if (restaurante != null) {
            if (id == null) {
                return false;
            }
            if (!restaurante.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
}
