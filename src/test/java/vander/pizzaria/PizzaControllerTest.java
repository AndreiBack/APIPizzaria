package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vander.pizzaria.controller.PizzaController;
import vander.pizzaria.dto.PizzaDTO;
import vander.pizzaria.service.PizzaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PizzaControllerTest {

    @InjectMocks
    private PizzaController controller;

    @Mock
    private PizzaService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllPizzas() {
        List<PizzaDTO> pizzas = new ArrayList<>();
        pizzas.add(new PizzaDTO());
        pizzas.add(new PizzaDTO());

        when(service.getAll()).thenReturn(pizzas);

        ResponseEntity<List<PizzaDTO>> response = controller.getAllPizzas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pizzas, response.getBody());

        verify(service, times(1)).getAll();
    }

    @Test
    void testCreatePizza() {
        PizzaDTO pizzaDTO = new PizzaDTO();
        PizzaDTO createdPizza = new PizzaDTO();

        when(service.create(pizzaDTO)).thenReturn(createdPizza);

        ResponseEntity<PizzaDTO> response = controller.createPizza(pizzaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdPizza, response.getBody());

        verify(service, times(1)).create(pizzaDTO);
    }

    @Test
    void testUpdatePizza() {
        Long pizzaId = 1L;
        PizzaDTO pizzaDTO = new PizzaDTO();
        PizzaDTO updatedPizza = new PizzaDTO();

        when(service.update(pizzaId, pizzaDTO)).thenReturn(updatedPizza);

        ResponseEntity<PizzaDTO> response = controller.updatePizza(pizzaId, pizzaDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPizza, response.getBody());

        verify(service, times(1)).update(pizzaId, pizzaDTO);
    }

    @Test
    void testUpdatePizzaNotFound() {
        Long pizzaId = 1L;
        PizzaDTO pizzaDTO = new PizzaDTO();

        when(service.update(pizzaId, pizzaDTO)).thenReturn(null);

        ResponseEntity<PizzaDTO> response = controller.updatePizza(pizzaId, pizzaDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());

        verify(service, times(1)).update(pizzaId, pizzaDTO);
    }

    @Test
    void testDeletePizza() {
        Long pizzaId = 1L;

        when(service.delete(pizzaId)).thenReturn(true);

        ResponseEntity<Void> response = controller.deletePizza(pizzaId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).delete(pizzaId);
    }

    @Test
    void testDeletePizzaNotFound() {
        Long pizzaId = 1L;

        when(service.delete(pizzaId)).thenReturn(false);

        ResponseEntity<Void> response = controller.deletePizza(pizzaId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(service, times(1)).delete(pizzaId);
    }
}
