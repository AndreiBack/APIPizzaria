package vander.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_pedido", schema = "public")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "status")
    boolean status;
    @Column(name = "quantidade", nullable = false)
    int quantidade;
    @Column(name = "valor", nullable = false)
    double valor;
    @Column(name = "data", nullable = false)
    Date dataHora;
    @ManyToOne
    @JoinColumn(name = "funcionarios")
    Funcionario funcionario;
    @ManyToMany
    @JoinTable( name = "pedido_pizza", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    List<Pizza> pizzas;
    @ManyToMany
    @JoinTable( name = "pedido_produtos", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    List<Produto> produtos;
}
