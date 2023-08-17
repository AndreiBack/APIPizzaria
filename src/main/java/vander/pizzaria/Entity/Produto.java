package vander.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_produto", schema = "public")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "nome", length = 100, nullable = false)
    String nome;
    @Column(name = "descricao", length = 250, nullable = false)
    String descricao;
    @Column(name = "valorTotal", nullable = false)
    BigDecimal valorTotal;
}
