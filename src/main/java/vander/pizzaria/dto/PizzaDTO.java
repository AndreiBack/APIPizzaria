package vander.pizzaria.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDTO {
    private Long id;
    private double valor;
    private String tamanho;
    private List<SaborDTO> sabores;


}
