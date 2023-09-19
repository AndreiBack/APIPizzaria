package vander.pizzaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vander.pizzaria.DTO.ClienteDTO;
import vander.pizzaria.Entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}

