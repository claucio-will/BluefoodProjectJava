package com.claucio.bluefood.domain.restaurante;

import com.claucio.bluefood.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "restaurante")
public class Restaurante extends User {

    @NotBlank(message = "CNPJ não pode ser vázio")
    @Column(length = 11, nullable = false)
    private String cnpj;

    @Size(max = 80)
    private String logotipo;

    private transient MultipartFile logotipoFile;

    @NotNull(message = "A taxa de entrega não pode ser vázia")
    @Min(0)
    @Max(99)
    private BigDecimal taxaEntrega;

    @NotNull(message = "O tempo de entrega não pode ser vázia")
    @Min(0)
    @Max(120)
    private Integer tempoEntregaBase;

    @ManyToMany
    @JoinTable(name = "restaurante_has_categoria",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_restaurante_id"))
    @Size(min = 1, message = "Necessário pelo menos uma categoria")
    @ToString.Exclude
    private Set<CategoryRestaurante> categorias = new HashSet<>(0);


    public void setLogotipoFileName(){
      if (getId() == null){
          throw new IllegalArgumentException("É preciso primeiro gravar o registro");
      }
      //TODO: trocar forma de ler extenção
      this.logotipo = String.format("%04d-lgo.%s", getId(), ".png");
    }

}
