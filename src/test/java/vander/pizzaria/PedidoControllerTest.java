package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vander.pizzaria.controller.PedidoController;
import vander.pizzaria.entity.Pedido;
import vander.pizzaria.service.PedidoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PedidoControllerTest {

    @InjectMocks
    private PedidoController controller;

    @Mock
    private PedidoService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido());
        pedidos.add(new Pedido());

        when(service.getAllPedidos()).thenReturn(pedidos);

        ResponseEntity<List<Pedido>> response = controller.getAllPedidos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidos, response.getBody());

        verify(service, times(1)).getAllPedidos();
    }

    @Test
    void testCreatePedido() {
        Pedido pedido = new Pedido();
        Pedido createdPedido = new Pedido();

        when(service.createPedido(pedido)).thenReturn(createdPedido);

        ResponseEntity<Pedido> response = controller.createPedido(pedido);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdPedido, response.getBody());

        verify(service, times(1)).createPedido(pedido);
    }

    @Test
    void testUpdatePedido() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido();
        Pedido updatedPedido = new Pedido();

        when(service.updatePedido(pedidoId, pedido)).thenReturn(updatedPedido);

        ResponseEntity<Pedido> response = controller.updatePedido(pedidoId, pedido);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPedido, response.getBody());

        verify(service, times(1)).updatePedido(pedidoId, pedido);
    }

    @Test
    void testUpdatePedidoNotFound() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido();

        when(service.updatePedido(pedidoId, pedido)).thenReturn(null);

        ResponseEntity<Pedido> response = controller.updatePedido(pedidoId, pedido);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());

        verify(service, times(1)).updatePedido(pedidoId, pedido);
    }

    @Test
    void testDeletePedido() {
        Long pedidoId = 1L;

        when(service.deletePedido(pedidoId)).thenReturn(true);

        ResponseEntity<Void> response = controller.deletePedido(pedidoId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).deletePedido(pedidoId);
    }

    @Test
    void testDeletePedidoNotFound() {
        Long pedidoId = 1L;

        when(service.deletePedido(pedidoId)).thenReturn(false);

        ResponseEntity<Void> response = controller.deletePedido(pedidoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(service, times(1)).deletePedido(pedidoId);
    }
}
