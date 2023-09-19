package vander.pizzaria.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vander.pizzaria.Entity.Pizza;
import vander.pizzaria.Entity.Sabor;
import vander.pizzaria.Repository.PizzaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Pizza createPizza(Pizza pizza) {
        if (!QuantSabor(pizza)) {
            throw new IllegalArgumentException("Número inválido de sabores para o tamanho da pizza.");
        }
        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(Long id, Pizza pizza) {
        Optional<Pizza> existingPizza = pizzaRepository.findById(id);
        if (existingPizza.isPresent()) {
            pizza.setId(id);
            if (!QuantSabor(pizza)) {
                throw new IllegalArgumentException("Número inválido de sabores para o tamanho da pizza.");
            }
            return pizzaRepository.save(pizza);
        }
        return null;
    }

    public boolean deletePizza(Long id) {
        Optional<Pizza> existingPizza = pizzaRepository.findById(id);
        if (existingPizza.isPresent()) {
            pizzaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean QuantSabor(Pizza pizza) {
        String tamanho = pizza.getTamanho();
        List<Sabor> sabores = pizza.getSabores();

        if (tamanho.equalsIgnoreCase("P") && sabores.size() != 1) {
            return false;
        } else if (tamanho.equalsIgnoreCase("M") && sabores.size() >= 2) {
            return false;
        } else if (tamanho.equalsIgnoreCase("G") && sabores.size() >= 3) {
            return false;
        } else if (tamanho.equalsIgnoreCase("GG") && sabores.size() >= 4) {
            return false;
        }
        return true;
    }
}
