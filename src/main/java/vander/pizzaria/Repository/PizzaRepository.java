package vander.pizzaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vander.pizzaria.Entity.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
