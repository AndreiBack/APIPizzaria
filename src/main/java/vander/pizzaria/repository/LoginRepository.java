package vander.pizzaria.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vander.pizzaria.entity.Usuario;

import java.util.Optional;


public interface LoginRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByUsername(String login);
	
}
