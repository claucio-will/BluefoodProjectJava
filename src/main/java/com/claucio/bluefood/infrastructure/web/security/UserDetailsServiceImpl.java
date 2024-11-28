package com.claucio.bluefood.infrastructure.web.security;

import com.claucio.bluefood.domain.client.ClientRepository;
import com.claucio.bluefood.domain.restaurante.RestauranteRepository;
import com.claucio.bluefood.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//Classe que faz a busca do usuário no banco
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Verifica no banco se existe cliente com email
        User user = clientRepository.findByEmail(username);

        //Caso for nulo vai verificar no repositorio do restaurante
        if (user == null){
            user = restauranteRepository.findByEmail(username);

            //Caso ainda for nulo lançar exceção porque não tem nenhum usuário
            if (user == null){
                throw new UsernameNotFoundException(username);
            }
        }


        return new LoggedUser(user);
    }
}
