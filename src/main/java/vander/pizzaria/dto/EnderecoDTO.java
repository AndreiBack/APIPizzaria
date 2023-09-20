package vander.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
    public class EnderecoDTO {
        private Long id;
        private String rua;
        private String bairro;
        private Integer numero;
        private String cep;

 }

