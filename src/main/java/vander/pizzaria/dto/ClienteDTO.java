package vander.pizzaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vander.pizzaria.entity.Endereco;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
    public class ClienteDTO {
        private Long id;
        private String nome;
        private int idade;
        private String cpf;
        private String email;
        private String senha;
        private String telefone;
        private List<Endereco> endereco;

}



