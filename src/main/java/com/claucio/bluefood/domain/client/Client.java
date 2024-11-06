package com.claucio.bluefood.domain.client;

import com.claucio.bluefood.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "clientes")
public class Client extends User implements Serializable {

    @NotBlank(message = "Cpf não pode ser vazio")
    //@Pattern(regexp = "[0-9]{11}", message = "Cpf possui formato inválido")
    @Column(length = 11)
    private String cpf;

    @NotBlank(message = "Cep não pode ser inválido")
   // @Pattern(regexp = "[0-9] {8}", message = "Cep possui formato inválido")
    @Column(length = 8)
    private String cep;
}
