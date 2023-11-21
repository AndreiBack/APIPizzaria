package vander.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vander.pizzaria.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
}
