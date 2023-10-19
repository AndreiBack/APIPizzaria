package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.dto.ProdutoDTO;
import vander.pizzaria.entity.Produto;
import vander.pizzaria.repository.ProdutoRepository;
import vander.pizzaria.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

 class ProdutoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    public void setUp(){MockitoAnnotations.openMocks(this);}

    @Test
     void testGetAllProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto());
        produtos.add(new Produto());
        when(produtoRepository.findAll()).thenReturn(produtos);
        List<ProdutoDTO> result = produtoService.getAll();
        assert result.size() == produtos.size();
       verify(produtoRepository, times(0)).save(any(Produto.class));

    }

    @Test
     void testCreateProduto() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Produto de Teste");
        produtoDTO.setDescricao("Descrição do Produto de Teste");
        produtoDTO.setValorTotal(10.0);

        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setValor(produtoDTO.getValorTotal());

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoDTO result = produtoService.create(produtoDTO);
        assert result != null;
        assert result.getId().equals(produto.getId());
       verify(produtoRepository, times(1)).save(any(Produto.class));

    }

    @Test
     void testUpdateProduto() {
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
        produto.setValor(produtoDTO.getValorTotal());

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoDTO updatedProduto = produtoService.update(id, produtoDTO);
        assert updatedProduto != null;
        assert updatedProduto.getId().equals(id);
       verify(produtoRepository, times(1)).save(any(Produto.class));

    }

    @Test
     void testDeleteProduto() {
        Long id = 1L;
        Produto produto = new Produto();
        produto.setId(id);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        boolean result = produtoService.delete(id);
        assert result;
       verify(produtoRepository, times(0)).save(any(Produto.class));

    }
}
