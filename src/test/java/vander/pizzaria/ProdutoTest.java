package vander.pizzaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
        List<Produto> result = produtoService.getAllProdutos();
        assert result.size() == produtos.size();
    }

    @Test
    public void testCreateProduto() {
        Produto produto = new Produto();
        produto.setNome("Produto de Teste");
        produto.setDescricao("Descrição do Produto de Teste");
        produto.setValorTotal(10.0);
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
        Produto result = produtoService.createProduto(produto);
        assert result != null;
    }

    @Test
    public void testUpdateProduto() {
        Long id = 1L;
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome("Produto Atualizado");
        produto.setDescricao("Descrição Atualizada");
        produto.setValorTotal(15.0);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
        Produto updatedProduto = produtoService.updateProduto(id, produto);
        assert updatedProduto != null;
        assert updatedProduto.getId().equals(id);
    }

    @Test
    public void testDeleteProduto() {
        Long id = 1L;
        Produto produto = new Produto();
        produto.setId(id);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        boolean result = produtoService.deleteProduto(id);
        assert result;
    }
}
