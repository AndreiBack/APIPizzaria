package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vander.pizzaria.controller.FuncionarioController;
import vander.pizzaria.dto.FuncionarioDTO;
import vander.pizzaria.entity.Funcionario;
import vander.pizzaria.service.FuncionarioService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FuncionarioControllerTest {

    @InjectMocks
    private FuncionarioController controller;

    @Mock
    private FuncionarioService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetFuncionario() {
        Long funcionarioId = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioId);

        when(service.findById(funcionarioId)).thenReturn(funcionario);

        ResponseEntity<Funcionario> response = controller.getFuncionario(funcionarioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(funcionario, response.getBody());

        verify(service, times(1)).findById(funcionarioId);
    }

    @Test
    void testGetAllFuncionarios() {
        List<FuncionarioDTO> funcionarioDTOs = new ArrayList<>();
        funcionarioDTOs.add(new FuncionarioDTO());
        funcionarioDTOs.add(new FuncionarioDTO());

        when(service.findAll()).thenReturn(funcionarioDTOs);

        ResponseEntity<List<FuncionarioDTO>> response = controller.getAllFuncionarios();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(funcionarioDTOs, response.getBody());

        verify(service, times(1)).findAll();
    }

    @Test
    void testCreateFuncionario() {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();

        ResponseEntity<Void> response = controller.createFuncionario(funcionarioDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        verify(service, times(1)).create(funcionarioDTO);
    }

    @Test
    void testUpdateFuncionario() {
        Long funcionarioId = 1L;
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();

        ResponseEntity<Void> response = controller.updateFuncionario(funcionarioId, funcionarioDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(service, times(1)).update(funcionarioId, funcionarioDTO);
    }

    @Test
    void testDeleteFuncionario() {
        Long funcionarioId = 1L;

        ResponseEntity<Void> response = controller.deleteFuncionario(funcionarioId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).delete(funcionarioId);
    }
}
