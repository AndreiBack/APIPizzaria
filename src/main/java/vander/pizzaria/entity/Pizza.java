package vander.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_pizza", schema = "public")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "valor", nullable = false)
    double valor;

    @Column(name = "tamanho", length = 1, nullable = false)
    String tamanho;

    @ManyToMany
    List<Sabor> sabores;
}
