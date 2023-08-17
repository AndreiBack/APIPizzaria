package vander.pizzaria.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import vander.pizzaria.Entity.Cliente;
import vander.pizzaria.Repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(final Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(final Cliente cliente) {
        validateCliente(cliente);
        clienteRepository.save(cliente);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(final Long id, final Cliente cliente) {
        Cliente clienteDatabase = findById(id);
        Assert.isTrue(clienteDatabase.getId().equals(cliente.getId()), "Clientes não conferem!");
        validateCliente(cliente);
        clienteRepository.save(cliente);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id) {
        Cliente cliente = findById(id);
        clienteRepository.delete(cliente);
    }

    private void validateCliente(final Cliente cliente) {
        Assert.notNull(cliente.getNome(), "Nome não pode ser nulo!");
        Assert.isTrue(!cliente.getNome().isBlank(), "Nome inválido!");

        Assert.notNull(cliente.getIdade(), "Idade não pode ser nula!");
        Assert.isTrue(cliente.getIdade() > 0, "Idade não pode ser negativa!");

        Assert.notNull(cliente.getEmail(), "Email não pode ser nulo!");
        Assert.isTrue(!cliente.getEmail().isBlank(), "Deve conter email!");
        Assert.isTrue(cliente.getEmail().matches("[a-zA-Z0-9]+@[a-z]+[.]{1}[a-z]+"), "Formato do email inválido!");

        Assert.notNull(cliente.getSenha(), "Senha não pode ser nula!");
        Assert.isTrue(!cliente.getSenha().isBlank(), "Deve conter senha!");
    }
}
