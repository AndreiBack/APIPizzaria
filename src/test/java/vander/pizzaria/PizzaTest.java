package vander.pizzaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.Entity.Pizza;
import vander.pizzaria.Repository.PizzaRepository;
import vander.pizzaria.Service.PizzaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class PizzaTest {

    @Mock
    private PizzaRepository pizzaRepository;

    @InjectMocks
    private PizzaService pizzaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza());
        pizzas.add(new Pizza());
        when(pizzaRepository.findAll()).thenReturn(pizzas);
        List<Pizza> result = pizzaService.getAllPizzas();
        assert result.size() == pizzas.size();
    }

    @Test
    public void testCreatePizza() {
        Pizza pizza = new Pizza();
        pizza.setValor(20.0);
        pizza.setTamanho("M");
        pizza.setSabores(new ArrayList<>());
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza);
        Pizza result = pizzaService.createPizza(pizza);
        assert result != null;
    }

    @Test
    public void testUpdatePizza() {
        Long id = 1L;
        Pizza pizza = new Pizza();
        pizza.setId(id);
        pizza.setValor(20.0);
        pizza.setTamanho("M");
        pizza.setSabores(new ArrayList<>());
        when(pizzaRepository.findById(id)).thenReturn(Optional.of(pizza));
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza);
        Pizza updatedPizza = pizzaService.updatePizza(id, pizza);
        assert updatedPizza != null;
        assert updatedPizza.getId().equals(id);
    }

    @Test
    public void testDeletePizza() {
        Long id = 1L;
        Pizza pizza = new Pizza();
        pizza.setId(id);
        when(pizzaRepository.findById(id)).thenReturn(Optional.of(pizza));
        boolean result = pizzaService.deletePizza(id);
        assert result;
    }

}
