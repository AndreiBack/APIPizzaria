package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.DTO.ProdutoDTO;
import vander.pizzaria.Entity.Produto;
import vander.pizzaria.Repository.ProdutoRepository;
import vander.pizzaria.Service.ProdutoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProdutoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto());
        produtos.add(new Produto());
        when(produtoRepository.findAll()).thenReturn(produtos);
        List<ProdutoDTO> result = produtoService.getAll();
        assert result.size() == produtos.size();
    }

    @Test
    public void testCreateProduto() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Produto de Teste");
        produtoDTO.setDescricao("Descrição do Produto de Teste");
        produtoDTO.setValorTotal(10.0);

        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setValorTotal(produtoDTO.getValorTotal());

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoDTO result = produtoService.create(produtoDTO);
        assert result != null;
        assert result.getId().equals(produto.getId());
    }

    @Test
    public void testUpdateProduto() {
        Long id = 1L;

        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(id);
        produtoDTO.setNome("Produto Atualizado");
        produtoDTO.setDescricao("Descrição Atualizada");
        produtoDTO.setValorTotal(15.0);

        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setValorTotal(produtoDTO.getValorTotal());

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoDTO updatedProduto = produtoService.update(id, produtoDTO);
        assert updatedProduto != null;
        assert updatedProduto.getId().equals(id);
    }

    @Test
    public void testDeleteProduto() {
        Long id = 1L;
        Produto produto = new Produto();
        produto.setId(id);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        boolean result = produtoService.delete(id);
        assert result;
    }
}
