package vander.pizzaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnderecoDTO {
    private Long id;
    private String cep;
    private String rua;
    private String bairro;
    private int numero;

}
