package vander.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vander.pizzaria.entity.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
