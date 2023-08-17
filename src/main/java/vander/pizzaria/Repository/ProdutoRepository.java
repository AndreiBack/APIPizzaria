package vander.pizzaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vander.pizzaria.Entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
