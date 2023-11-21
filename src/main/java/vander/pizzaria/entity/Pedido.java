package vander.pizzaria.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    Status status;
    @Column(name = "quantidade", nullable = false)
    int quantidade;
    @Column(name = "valor", nullable = false)
    double valorTotal;
    @Column(name = "observacao")
    String observacao;
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    Date dataHora;
    @ManyToOne
    @JoinColumn(name = "Cliente")
    Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "funcionarios")
    Funcionario funcionario;
    @ManyToMany
    @JoinTable( name = "pedido_pizza", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    List<Pizza> pizzas;
    @ManyToMany
    @JoinTable( name = "pedido_produtos", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    List<Produto> produtos;
    @PrePersist
    public void prePersist() {
        dataHora = new Date();
    }
}
