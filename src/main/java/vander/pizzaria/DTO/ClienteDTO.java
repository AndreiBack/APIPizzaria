package vander.pizzaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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



