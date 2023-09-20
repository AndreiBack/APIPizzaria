package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vander.pizzaria.controller.ClienteController;
import vander.pizzaria.dto.ClienteDTO;
import vander.pizzaria.service.ClienteService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @InjectMocks
    private ClienteController controller;

    @Mock
    private ClienteService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetClienteById() {
        Long clienteId = 1L;
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(clienteId);

        when(service.findById(clienteId)).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO> response = controller.getClienteById(clienteId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDTO, response.getBody());

        verify(service, times(1)).findById(clienteId);
    }

    @Test
    void testGetAllClientes() {
        List<ClienteDTO> clienteDTOs = new ArrayList<>();
        clienteDTOs.add(new ClienteDTO());
        clienteDTOs.add(new ClienteDTO());

        when(service.findAll()).thenReturn(clienteDTOs);

        ResponseEntity<List<ClienteDTO>> response = controller.getAllClientes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDTOs, response.getBody());

        verify(service, times(1)).findAll();
    }

    @Test
    void testCreateCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        String responseMessage = "Cliente criado com sucesso.";

        when(service.create(clienteDTO)).thenReturn(responseMessage);

        ResponseEntity<String> response = controller.createCliente(clienteDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseMessage, response.getBody());

        verify(service, times(1)).create(clienteDTO);
    }

    @Test
    void testUpdateCliente() {
        Long clienteId = 1L;
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(clienteId);
        String responseMessage = "Cliente atualizado com sucesso.";

        when(service.update(clienteId, clienteDTO)).thenReturn(responseMessage);

        ResponseEntity<String> response = controller.updateCliente(clienteId, clienteDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMessage, response.getBody());

        verify(service, times(1)).update(clienteId, clienteDTO);
    }

    @Test
    void testDeleteCliente() {
        Long clienteId = 1L;

        ResponseEntity<Void> response = controller.deleteCliente(clienteId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).delete(clienteId);
    }
}