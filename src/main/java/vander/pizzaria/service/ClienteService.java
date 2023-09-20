package vander.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import vander.pizzaria.dto.ClienteDTO;
import vander.pizzaria.entity.Cliente;
import vander.pizzaria.repository.ClienteRepository;

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
    public void create(final ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        validateCliente(cliente);
        clienteRepository.save(cliente);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(final Long id, final ClienteDTO clienteDTO) {
        Cliente clienteDatabase = findById(id);
        Assert.isTrue(clienteDatabase.getId().equals(clienteDTO.getId()), "Clientes não conferem!");

        Cliente cliente = convertToEntity(clienteDTO);
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

        Assert.isTrue(cliente.getCpf().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"), "CPF inválido");

        Assert.isTrue(cliente.getTelefone().matches("\\(\\d{2}\\)\\d{5}-\\d{4}"), "Telefone inválido");

        Assert.notNull(cliente.getEmail(), "Email não pode ser nulo!");
        Assert.isTrue(!cliente.getEmail().isBlank(), "Deve conter email!");
        Assert.isTrue(cliente.getEmail().matches("[a-zA-Z0-9]+@[a-z]+[.][a-z]+"), "Formato do email inválido!");

        Assert.notNull(cliente.getSenha(), "Senha não pode ser nula!");
        Assert.isTrue(!cliente.getSenha().isBlank(), "Deve conter senha!");
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSenha(clienteDTO.getSenha());
        cliente.setTelefone(clienteDTO.getTelefone());
        return cliente;
    }
}