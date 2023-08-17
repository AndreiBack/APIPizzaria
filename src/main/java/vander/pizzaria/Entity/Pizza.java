package vander.pizzaria.Entity;

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
    @Column(name = "nome", length = 100, nullable = false)
    String nome;
    @Column(name = "tamanho", length = 1, nullable = false)
    String tamanho;
    @ManyToOne
    @JoinColumn(name = "produto", nullable = false)
    Produto produto;
}
