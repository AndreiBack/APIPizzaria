package vander.pizzaria.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vander.pizzaria.Entity.Pizza;
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
        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(Long id, Pizza pizza) {
        Optional<Pizza> existingPizza = pizzaRepository.findById(id);
        if (existingPizza.isPresent()) {
            pizza.setId(id);
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
}
