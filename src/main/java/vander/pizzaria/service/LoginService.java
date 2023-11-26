//AuthenticationService.java
package vander.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import vander.pizzaria.config.JwtServiceGenerator;
import vander.pizzaria.dto.LoginDTO;
import vander.pizzaria.dto.UsuarioDTO;
import vander.pizzaria.entity.Usuario;
import vander.pizzaria.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository repository;
	@Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;


	public UsuarioDTO logar(LoginDTO loginDTO) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginDTO.getUsername(),
						loginDTO.getPassword()
						)
				);
		Usuario user = repository.findByUsername(loginDTO.getUsername()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		
		return toUserDTO(user, jwtToken);
	}


	private UsuarioDTO toUserDTO(Usuario user, String token) {
		UsuarioDTO userDTO = new UsuarioDTO();
		userDTO.setId(user.getId());
		userDTO.setRole(user.getRole());
		userDTO.setToken(token);
		userDTO.setUsername(user.getUsername());
		return userDTO;
	}

}
