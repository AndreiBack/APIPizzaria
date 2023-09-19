package vander.pizzaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
    public class ClienteDTO {
        private Long id;
        private String nome;
        private int idade;
        private String cpf;
        private String email;
        private String telefone;

    }


