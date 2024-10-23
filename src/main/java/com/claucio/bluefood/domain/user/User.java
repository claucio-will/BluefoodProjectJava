package com.claucio.bluefood.domain.user;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class User implements Serializable {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome obrigatório")
    @Size(max = 80, message = "Nome muito grande")
    private String name;

    @NotBlank(message = "Email obrigatório")
    @Size(max = 80, message = "Email muito grande")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha obrigatoria")
    @Size(max = 80, message = "Senha muito grande")
    private String password;

    @NotBlank(message = "Telefone obrigatório")
    @Pattern(regexp = "[0-9]{10,11}", message = "Telefone possui formato inválido")
    @Column(length = 11, nullable = false)
    private String phone;
}
