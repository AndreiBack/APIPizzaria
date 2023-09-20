package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.DTO.PizzaDTO;
import vander.pizzaria.DTO.SaborDTO;
import vander.pizzaria.Entity.Pizza;
import vander.pizzaria.Entity.Sabor;
import vander.pizzaria.Repository.PizzaRepository;
import vander.pizzaria.Service.PizzaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        Pizza pizza1 = new Pizza();
        pizza1.setSabores(new ArrayList<>());
        pizzas.add(pizza1);
        Pizza pizza2 = new Pizza();
        pizza2.setSabores(new ArrayList<>());
        when(pizzaRepository.findAll()).thenReturn(pizzas);
        List<PizzaDTO> result = pizzaService.getAll();
        assert result.size() == pizzas.size();
    }


    @Test
    public void testCreatePizza() {
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setValor(20.0);
        pizzaDTO.setTamanho("M");
        pizzaDTO.setSabores(new ArrayList<>());
        Pizza pizza = pizzaService.convertToEntity(pizzaDTO);
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza);
        PizzaDTO result = pizzaService.create(pizzaDTO);
        assert result != null;
    }

    @Test
    public void testUpdatePizza() {
        Long id = 1L;
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(id);
        pizzaDTO.setValor(20.0);
        pizzaDTO.setTamanho("M");
        pizzaDTO.setSabores(new ArrayList<>());
        Pizza pizza = pizzaService.convertToEntity(pizzaDTO);
        when(pizzaRepository.findById(id)).thenReturn(Optional.of(pizza));
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza);
        PizzaDTO updatedPizza = pizzaService.update(id, pizzaDTO);
        assert updatedPizza != null;
        assert updatedPizza.getId().equals(id);
    }

    @Test
    public void testDeletePizza() {
        Long id = 1L;
        Pizza pizza = new Pizza();
        pizza.setId(id);
        when(pizzaRepository.findById(id)).thenReturn(Optional.of(pizza));
        boolean result = pizzaService.delete(id);
        assert result;
    }

    @Test
    public void testQuantSabor() {
        Pizza pizzaP = new Pizza();
        pizzaP.setTamanho("P");
        List<Sabor> saboresP = new ArrayList<>();
        saboresP.add(new Sabor());
        pizzaP.setSabores(saboresP);
        assertTrue(pizzaService.quantidadeSabor(pizzaP));

        Pizza pizzaP2 = new Pizza();
        pizzaP2.setTamanho("P");
        List<Sabor> saboresP2 = new ArrayList<>();
        saboresP2.add(new Sabor());
        saboresP2.add(new Sabor());
        pizzaP2.setSabores(saboresP2);
        assertFalse(pizzaService.quantidadeSabor(pizzaP2));

        Pizza pizzaM = new Pizza();
        pizzaM.setTamanho("M");
        List<Sabor> saboresM = new ArrayList<>();
        saboresM.add(new Sabor());
        pizzaM.setSabores(saboresM);
        assertTrue(pizzaService.quantidadeSabor(pizzaM));

        Pizza pizzaM2 = new Pizza();
        pizzaM2.setTamanho("M");
        List<Sabor> saboresM2 = new ArrayList<>();
        saboresM2.add(new Sabor());
        saboresM2.add(new Sabor());
        saboresM2.add(new Sabor());
        pizzaM2.setSabores(saboresM2);
        assertFalse(pizzaService.quantidadeSabor(pizzaM2));

        Pizza pizzaG = new Pizza();
        pizzaG.setTamanho("G");
        List<Sabor> saboresG = new ArrayList<>();
        saboresG.add(new Sabor());
        pizzaG.setSabores(saboresG);
        assertTrue(pizzaService.quantidadeSabor(pizzaG));

        Pizza pizzaG2 = new Pizza();
        pizzaG2.setTamanho("G");
        List<Sabor> saboresG2 = new ArrayList<>();
        saboresG2.add(new Sabor());
        saboresG2.add(new Sabor());
        saboresG2.add(new Sabor());
        saboresG2.add(new Sabor());
        pizzaG2.setSabores(saboresG2);
        assertFalse(pizzaService.quantidadeSabor(pizzaG2));

        Pizza pizzaGG = new Pizza();
        pizzaGG.setTamanho("GG");
        List<Sabor> saboresGG = new ArrayList<>();
        saboresGG.add(new Sabor());
        pizzaGG.setSabores(saboresGG);
        assertTrue(pizzaService.quantidadeSabor(pizzaGG));

        Pizza pizzaGG2 = new Pizza();
        pizzaGG2.setTamanho("GG");
        List<Sabor> saboresGG2 = new ArrayList<>();
        saboresGG2.add(new Sabor());
        saboresGG2.add(new Sabor());
        saboresGG2.add(new Sabor());
        saboresGG2.add(new Sabor());
        saboresGG2.add(new Sabor());
        pizzaGG2.setSabores(saboresGG2);
        assertFalse(pizzaService.quantidadeSabor(pizzaGG2));
    }
    @Test
    public void testConvertToSabor() {
        // Crie um objeto SaborDTO
        SaborDTO saborDTO = new SaborDTO();
        saborDTO.setId(1L);
        saborDTO.setNome("Sabor Teste");
        List<String> ingredientes = Arrays.asList("Ingrediente 1", "Ingrediente 2");
        saborDTO.setIngredientes(ingredientes);

        // Chame o método para converter para Sabor
        Sabor sabor = convertToSabor(saborDTO);

        // Verifique se os campos foram convertidos corretamente
        assertEquals(sabor.getId(), saborDTO.getId());
        assertEquals(sabor.getNome(), saborDTO.getNome());
        assertEquals(sabor.getIngredientes(), saborDTO.getIngredientes());
    }

    @Test
    public void testConvertToSaborDTO() {
        // Crie um objeto Sabor
        Sabor sabor = new Sabor();
        sabor.setId(1L);
        sabor.setNome("Sabor Teste");
        List<String> ingredientes = Arrays.asList("Ingrediente 1", "Ingrediente 2");
        sabor.setIngredientes(ingredientes);

        // Chame o método para converter para SaborDTO
        SaborDTO saborDTO = convertToSaborDTO(sabor);

        // Verifique se os campos foram convertidos corretamente
        assertEquals(saborDTO.getId(), sabor.getId());
        assertEquals(saborDTO.getNome(), sabor.getNome());
        assertEquals(saborDTO.getIngredientes(), sabor.getIngredientes());
    }

    // Substitua esses métodos pelos métodos reais em seu código

    private Sabor convertToSabor(SaborDTO saborDTO) {
        Sabor sabor = new Sabor();
        sabor.setId(saborDTO.getId());
        sabor.setNome(saborDTO.getNome());
        sabor.setIngredientes(saborDTO.getIngredientes());
        return sabor;
    }

    private SaborDTO convertToSaborDTO(Sabor sabor) {
        SaborDTO saborDTO = new SaborDTO();
        saborDTO.setId(sabor.getId());
        saborDTO.setNome(sabor.getNome());
        saborDTO.setIngredientes(sabor.getIngredientes());
        return saborDTO;
    }

}
