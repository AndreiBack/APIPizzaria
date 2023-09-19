package vander.pizzaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private boolean status;
    private int quantidade;
    private double valor;
    private Date dataHora;
    private ClienteDTO cliente;
    private FuncionarioDTO funcionario;
    private List<PizzaDTO> pizzas;
    private List<ProdutoDTO> produtos;
}
