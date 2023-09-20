package vander.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vander.pizzaria.entity.Sabor;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Long> {
}
