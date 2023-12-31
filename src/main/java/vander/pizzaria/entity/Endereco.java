package vander.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "tb_endereco", schema = "public")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "cep", length = 10, nullable = false)
    String cep;
    @Column(name = "rua", length = 100, nullable = false)
    String rua;
    @Column(name = "bairro", length = 100, nullable = false)
    String bairro;
    @Column(name = "numero", nullable = false)
    int numero;

}
