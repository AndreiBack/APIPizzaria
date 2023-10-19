package vander.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vander.pizzaria.dto.ProdutoDTO;
import vander.pizzaria.entity.Produto;
import vander.pizzaria.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> getAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(this::mapToProdutoDTO)
                .toList();
    }


    public ProdutoDTO create(ProdutoDTO produtoDTO) {
        validateProdutoDTO(produtoDTO);

        Produto produto = mapToProduto(produtoDTO);
        Produto savedProduto = produtoRepository.save(produto);
        return mapToProdutoDTO(savedProduto);
    }

    public ProdutoDTO update(Long id, ProdutoDTO produtoDTO) {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if (existingProduto.isPresent()) {
            validateProdutoDTO(produtoDTO);

            Produto produtoToUpdate = existingProduto.get();
            produtoToUpdate.setNome(produtoDTO.getNome());
            produtoToUpdate.setDescricao(produtoDTO.getDescricao());
            produtoToUpdate.setValor(produtoDTO.getValor());

            Produto updatedProduto = produtoRepository.save(produtoToUpdate);
            return mapToProdutoDTO(updatedProduto);
        }
        return null;
    }

    public boolean delete(Long id) {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if (existingProduto.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validateProdutoDTO(ProdutoDTO produtoDTO) {
        Assert.notNull(produtoDTO.getNome(), "Nome não pode ser nulo!");
        Assert.isTrue(!produtoDTO.getNome().isBlank(), "Nome inválido!");

        Assert.notNull(produtoDTO.getDescricao(), "Descrição não pode ser nula!");
        Assert.isTrue(!produtoDTO.getDescricao().isBlank(), "Descrição inválida!");

        Assert.isTrue(produtoDTO.getValor() >= 0, "Valor total não pode ser negativo!");
    }

    private ProdutoDTO mapToProdutoDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getValor()
        );
    }

    private Produto mapToProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setId(produtoDTO.getId());
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setValor(produtoDTO.getValor());
        return produto;
    }
}
