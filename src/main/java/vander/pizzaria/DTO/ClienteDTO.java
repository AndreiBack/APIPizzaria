package vander.pizzaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}



