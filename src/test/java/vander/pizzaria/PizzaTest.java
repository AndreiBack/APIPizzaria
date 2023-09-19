package vander.pizzaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.Entity.Pizza;
import vander.pizzaria.Entity.Sabor;
import vander.pizzaria.Repository.PizzaRepository;
import vander.pizzaria.Service.PizzaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    @Test
    public void testQuantSabor() {
        // Teste para tamanho "P" e quantidade de sabores correta (1)
        Pizza pizzaP = new Pizza();
        pizzaP.setTamanho("P");
        List<Sabor> saboresP = new ArrayList<>();
        saboresP.add(new Sabor());
        pizzaP.setSabores(saboresP);
        assertTrue(pizzaService.QuantSabor(pizzaP));

        // Teste para tamanho "P" e quantidade de sabores incorreta (2)
        Pizza pizzaP2 = new Pizza();
        pizzaP2.setTamanho("P");
        List<Sabor> saboresP2 = new ArrayList<>();
        saboresP2.add(new Sabor());
        saboresP2.add(new Sabor());
        pizzaP2.setSabores(saboresP2);
        assertFalse(pizzaService.QuantSabor(pizzaP2));

        // Teste para tamanho "M" e quantidade de sabores correta (até 2)
        Pizza pizzaM = new Pizza();
        pizzaM.setTamanho("M");
        List<Sabor> saboresM = new ArrayList<>();
        saboresM.add(new Sabor());
        pizzaM.setSabores(saboresM);
        assertTrue(pizzaService.QuantSabor(pizzaM));

        // Teste para tamanho "M" e quantidade de sabores incorreta (3)
        Pizza pizzaM2 = new Pizza();
        pizzaM2.setTamanho("M");
        List<Sabor> saboresM2 = new ArrayList<>();
        saboresM2.add(new Sabor());
        saboresM2.add(new Sabor());
        saboresM2.add(new Sabor());
        pizzaM2.setSabores(saboresM2);
        assertFalse(pizzaService.QuantSabor(pizzaM2));

        // Teste para tamanho "G" e quantidade de sabores correta (até 3)
        Pizza pizzaG = new Pizza();
        pizzaG.setTamanho("G");
        List<Sabor> saboresG = new ArrayList<>();
        saboresG.add(new Sabor());
        pizzaG.setSabores(saboresG);
        assertTrue(pizzaService.QuantSabor(pizzaG));

        // Teste para tamanho "G" e quantidade de sabores incorreta (4)
        Pizza pizzaG2 = new Pizza();
        pizzaG2.setTamanho("G");
        List<Sabor> saboresG2 = new ArrayList<>();
        saboresG2.add(new Sabor());
        saboresG2.add(new Sabor());
        saboresG2.add(new Sabor());
        saboresG2.add(new Sabor());
        pizzaG2.setSabores(saboresG2);
        assertFalse(pizzaService.QuantSabor(pizzaG2));

        // Teste para tamanho "GG" e quantidade de sabores correta (até 4)
        Pizza pizzaGG = new Pizza();
        pizzaGG.setTamanho("GG");
        List<Sabor> saboresGG = new ArrayList<>();
        saboresGG.add(new Sabor());
        pizzaGG.setSabores(saboresGG);
        assertTrue(pizzaService.QuantSabor(pizzaGG));

        // Teste para tamanho "GG" e quantidade de sabores incorreta (5)
        Pizza pizzaGG2 = new Pizza();
        pizzaGG2.setTamanho("GG");
        List<Sabor> saboresGG2 = new ArrayList<>();
        saboresGG2.add(new Sabor());
        saboresGG2.add(new Sabor());
        saboresGG2.add(new Sabor());
        saboresGG2.add(new Sabor());
        saboresGG2.add(new Sabor());
        pizzaGG2.setSabores(saboresGG2);
        assertFalse(pizzaService.QuantSabor(pizzaGG2));
    }
}



