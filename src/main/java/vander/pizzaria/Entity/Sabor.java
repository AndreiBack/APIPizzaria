package vander.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_sabor", schema = "public")
public class Sabor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "ingredientes", nullable = false)
    List<String> ingredientes;
    @ManyToMany
    List<Pizza> pizzas;
}
