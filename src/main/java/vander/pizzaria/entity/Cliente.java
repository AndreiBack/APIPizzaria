package vander.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "tb_cliente", schema = "public")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "nome", length = 100, nullable = false)
    String nome;
    @Column(name = "idade", nullable = false)
    int idade;
    @Column(name = "cpf", length = 14, nullable = false)
    String cpf;
    @Column(name = "email", length = 150)
    String email;
    @Column(name = "senha", length = 40)
    String senha;
    @Column(name = "telefone", length = 15)
    String telefone;
    @ManyToMany
    @JoinColumn(name = "endereco_id")
    private List<Endereco> endereco;
}
