package vander.pizzaria.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import vander.pizzaria.Entity.Endereco;
import vander.pizzaria.Repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco findById(final Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()) {
            return endereco.get();
        }
        throw new RuntimeException("Endereço não encontrado");
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(final Endereco endereco) {
        validateEndereco(endereco);
        enderecoRepository.save(endereco);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(final Long id, final Endereco endereco) {
        Endereco enderecoDatabase = findById(id);
        Assert.isTrue(enderecoDatabase.getId().equals(endereco.getId()), "Endereços não conferem!");
        validateEndereco(endereco);
        enderecoRepository.save(endereco);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id) {
        Endereco endereco = findById(id);
        enderecoRepository.delete(endereco);
    }

    private void validateEndereco(final Endereco endereco) {
        Assert.isTrue(!endereco.getBairro().isBlank(), "Bairro inválido!");
        Assert.isTrue(!endereco.getRua().isBlank(), "Rua inválida!");
        Assert.notNull(endereco.getNumero(), "Número da residência não pode ser nulo!");
        Assert.isTrue(endereco.getCep().matches("\\d{5}-\\d{3}"), "CEP invalido");
        Assert.isTrue(endereco.getNumero() > 0, "Número da residência não pode ser negativo!");
    }
}