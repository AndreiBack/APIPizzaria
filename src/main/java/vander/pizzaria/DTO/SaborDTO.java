package vander.pizzaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SaborDTO {
    private Long id;
    private String nome;
    private List<String> ingredientes;
}

