package com.claucio.bluefood.domain.client;

import com.claucio.bluefood.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true , callSuper = true)
@Entity
public class Client extends User implements Serializable {


    private String cpf;


    private String cep;
}
