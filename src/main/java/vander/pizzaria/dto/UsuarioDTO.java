package vander.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

	private Long id;
	private String username;
	private String role;
	private String token;
	
}
