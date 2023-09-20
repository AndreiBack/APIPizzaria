package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vander.pizzaria.controller.EnderecoController;
import vander.pizzaria.dto.EnderecoDTO;
import vander.pizzaria.service.EnderecoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EnderecoControllerTest {

    @InjectMocks
    private EnderecoController controller;

    @Mock
    private EnderecoService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetEnderecoById() {
        Long enderecoId = 1L;
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(enderecoId);

        when(service.findById(enderecoId)).thenReturn(enderecoDTO);

        ResponseEntity<EnderecoDTO> response = controller.getEnderecoById(enderecoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(enderecoDTO, response.getBody());

        verify(service, times(1)).findById(enderecoId);
    }

    @Test
    void testGetAllEnderecos() {
        List<EnderecoDTO> enderecoDTOs = new ArrayList<>();
        enderecoDTOs.add(new EnderecoDTO());
        enderecoDTOs.add(new EnderecoDTO());

        when(service.findAll()).thenReturn(enderecoDTOs);

        ResponseEntity<List<EnderecoDTO>> response = controller.getAllEnderecos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(enderecoDTOs, response.getBody());

        verify(service, times(1)).findAll();
    }

    @Test
    void testCreateEndereco() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        String responseMessage = "Endereço criado com sucesso.";

        when(service.create(enderecoDTO)).thenReturn(responseMessage);

        ResponseEntity<String> response = controller.createEndereco(enderecoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseMessage, response.getBody());

        verify(service, times(1)).create(enderecoDTO);
    }

    @Test
    void testUpdateEndereco() {
        Long enderecoId = 1L;
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(enderecoId);
        String responseMessage = "Endereço atualizado com sucesso.";

        when(service.update(enderecoId, enderecoDTO)).thenReturn(responseMessage);

        ResponseEntity<String> response = controller.updateEndereco(enderecoId, enderecoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMessage, response.getBody());

        verify(service, times(1)).update(enderecoId, enderecoDTO);
    }

    @Test
    void testDeleteEndereco() {
        Long enderecoId = 1L;

        ResponseEntity<Void> response = controller.deleteEndereco(enderecoId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).delete(enderecoId);
    }
}
