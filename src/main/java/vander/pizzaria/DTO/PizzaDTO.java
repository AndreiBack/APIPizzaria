package vander.pizzaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PizzaDTO {
    private Long id;
    private double valor;
    private String tamanho;
    private List<SaborDTO> sabores;
}
