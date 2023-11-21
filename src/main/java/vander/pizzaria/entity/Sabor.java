package vander.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_sabor", schema = "public")
public class Sabor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "nome", length = 100, nullable = false)
    String nome;
    @Column(name = "ingredientes", nullable = false)
    String ingredientes;
}