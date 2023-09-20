package vander.pizzaria.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private Long id;
    private String nome;
    private int idade;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;

}

