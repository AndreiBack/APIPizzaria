package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vander.pizzaria.controller.ProdutoController;
import vander.pizzaria.dto.ProdutoDTO;
import vander.pizzaria.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController controller;

    @Mock
    private ProdutoService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProdutos() {
        List<ProdutoDTO> produtos = new ArrayList<>();
        produtos.add(new ProdutoDTO());
        produtos.add(new ProdutoDTO());

        when(service.getAll()).thenReturn(produtos);

        ResponseEntity<List<ProdutoDTO>> response = controller.getAllProdutos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produtos, response.getBody());

        verify(service, times(1)).getAll();
    }

    @Test
    void testCreateProduto() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        ProdutoDTO createdProduto = new ProdutoDTO();

        when(service.create(produtoDTO)).thenReturn(createdProduto);

        ResponseEntity<ProdutoDTO> response = controller.createProduto(produtoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdProduto, response.getBody());

        verify(service, times(1)).create(produtoDTO);
    }

    @Test
    void testUpdateProduto() {
        Long produtoId = 1L;
        ProdutoDTO produtoDTO = new ProdutoDTO();
        ProdutoDTO updatedProduto = new ProdutoDTO();

        when(service.update(produtoId, produtoDTO)).thenReturn(updatedProduto);

        ResponseEntity<ProdutoDTO> response = controller.updateProduto(produtoId, produtoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduto, response.getBody());

        verify(service, times(1)).update(produtoId, produtoDTO);
    }

    @Test
    void testUpdateProdutoNotFound() {
        Long produtoId = 1L;
        ProdutoDTO produtoDTO = new ProdutoDTO();

        when(service.update(produtoId, produtoDTO)).thenReturn(null);

        ResponseEntity<ProdutoDTO> response = controller.updateProduto(produtoId, produtoDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());

        verify(service, times(1)).update(produtoId, produtoDTO);
    }

    @Test
    void testDeleteProduto() {
        Long produtoId = 1L;

        when(service.delete(produtoId)).thenReturn(true);

        ResponseEntity<Void> response = controller.deleteProduto(produtoId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).delete(produtoId);
    }

    @Test
    void testDeleteProdutoNotFound() {
        Long produtoId = 1L;

        when(service.delete(produtoId)).thenReturn(false);

        ResponseEntity<Void> response = controller.deleteProduto(produtoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(service, times(1)).delete(produtoId);
    }
}
