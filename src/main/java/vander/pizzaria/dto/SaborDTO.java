package vander.pizzaria.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaborDTO {
    private Long id;
    private String nome;
    private List<String> ingredientes;


}

