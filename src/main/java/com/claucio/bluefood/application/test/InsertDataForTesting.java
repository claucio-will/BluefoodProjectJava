package com.claucio.bluefood.application.test;

import com.claucio.bluefood.domain.client.Client;
import com.claucio.bluefood.domain.client.ClientRepository;
import com.claucio.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import com.claucio.bluefood.domain.restaurante.CategoryRestaurante;
import com.claucio.bluefood.domain.restaurante.Restaurante;
import com.claucio.bluefood.domain.restaurante.RestauranteRepository;
import com.claucio.bluefood.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class InsertDataForTesting {

    @Autowired
    private ClientRepository clientRepository;


    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CategoriaRestauranteRepository categoriaRestauranteRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event){
     clientes();
     restaurantes();

    }


    private Restaurante[] restaurantes() {
        List<Restaurante> restaurantes = new ArrayList<>();

        CategoryRestaurante categoriaPizza = categoriaRestauranteRepository.findById(1).orElseThrow();
        CategoryRestaurante categoriaSanduiche = categoriaRestauranteRepository.findById(2).orElseThrow();
        CategoryRestaurante categoriaSobremesa = categoriaRestauranteRepository.findById(5).orElseThrow();
        CategoryRestaurante categoriaJapones = categoriaRestauranteRepository.findById(6).orElseThrow();

        Restaurante r = new Restaurante();
        r.setName("Bubger King");
        r.setEmail("r1@bluefood.com.br");
        r.setPassword(StringUtils.encrypt("r"));
        r.setCnpj("00000000000");
        r.setTaxaEntrega(BigDecimal.valueOf(3.2));
        r.setPhone("99876671010");
        r.getCategorias().add(categoriaSanduiche);
        r.getCategorias().add(categoriaSobremesa);
        r.setLogotipo("0001-logo.png");
        r.setTempoEntregaBase(30);
        restauranteRepository.save(r);
        restaurantes.add(r);

        r = new Restaurante();
        r.setName("Mc Naldo's");
        r.setEmail("r2@bluefood.com.br");
        r.setPassword(StringUtils.encrypt("r"));
        r.setCnpj("0000000002");
        r.setTaxaEntrega(BigDecimal.valueOf(4.5));
        r.setPhone("99876671011");
        r.getCategorias().add(categoriaSanduiche);
        r.getCategorias().add(categoriaSobremesa);
        r.setLogotipo("0002-logo.png");
        r.setTempoEntregaBase(25);
        restauranteRepository.save(r);
        restaurantes.add(r);

        r = new Restaurante();
        r.setName("Sbubby");
        r.setEmail("r3@bluefood.com.br");
        r.setPassword(StringUtils.encrypt("r"));
        r.setCnpj("00000000003");
        r.setTaxaEntrega(BigDecimal.valueOf(12.2));
        r.setPhone("99876671012");
        r.getCategorias().add(categoriaSanduiche);
        r.getCategorias().add(categoriaSobremesa);
        r.setLogotipo("0003-logo.png");
        r.setTempoEntregaBase(38);
        restauranteRepository.save(r);
        restaurantes.add(r);

        r = new Restaurante();
        r.setName("Pizza Brut");
        r.setEmail("r4@bluefood.com.br");
        r.setPassword(StringUtils.encrypt("r"));
        r.setCnpj("00000000004");
        r.setTaxaEntrega(BigDecimal.valueOf(9.8));
        r.setPhone("99876671013");
        r.getCategorias().add(categoriaPizza);
        r.getCategorias().add(categoriaSobremesa);
        r.setLogotipo("0004-logo.png");
        r.setTempoEntregaBase(22);
        restauranteRepository.save(r);
        restaurantes.add(r);

        r = new Restaurante();
        r.setName("Wiki Japa");
        r.setEmail("r5@bluefood.com.br");
        r.setPassword(StringUtils.encrypt("r"));
        r.setCnpj("00000000005");
        r.setTaxaEntrega(BigDecimal.valueOf(14.9));
        r.setPhone("99876671014");
        r.getCategorias().add(categoriaJapones);
        r.getCategorias().add(categoriaSobremesa);
        r.setLogotipo("0005-logo.png");
        r.setTempoEntregaBase(19);
        restauranteRepository.save(r);
        restaurantes.add(r);

        Restaurante[] array = new Restaurante[restaurantes.size()];
        return restaurantes.toArray(array);
    }

    private Client[] clientes() {
        List<Client> clientes = new ArrayList<>();

        Client c = new Client();
        c.setName("João Silva");
        c.setEmail("joao@bluefood.com.br");
        c.setPassword(StringUtils.encrypt("c"));
        c.setCep("89300100");
        c.setCpf("03099887666");
        c.setPhone("99355430001");
        clientes.add(c);
        clientRepository.save(c);

        c = new Client();
        c.setName("Maria Torres");
        c.setEmail("maria@bluefood.com.br");
        c.setPassword(StringUtils.encrypt("c"));
        c.setCep("89300101");
        c.setCpf("03099887677");
        c.setPhone("99355430002");
        clientes.add(c);
        clientRepository.save(c);

        Client[] array = new Client[clientes.size()];
        return clientes.toArray(array);
    }

}
