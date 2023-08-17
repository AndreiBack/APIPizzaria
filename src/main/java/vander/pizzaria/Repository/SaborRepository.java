package vander.pizzaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vander.pizzaria.Entity.Sabor;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Long> {
}
