package vander.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import vander.pizzaria.dto.ClienteDTO;
import org.modelmapper.ModelMapper;
import vander.pizzaria.entity.Cliente;
import vander.pizzaria.repository.ClienteRepository;
import vander.pizzaria.repository.EnderecoRepository;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ModelMapper modelMapper;

    private Cliente toCliente(ClienteDTO clienteDTO) {
        return modelMapper.map(clienteDTO, Cliente.class);
    }

    private ClienteDTO toClienteDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    private void idNotNull(Long id) {
        Assert.notNull(clienteRepository.findById(id).orElse(null), String.format("ID [%s] não encontrado", id));
    }

    private void validationClienteDTO(ClienteDTO clienteDTO) {
        Assert.notNull(clienteDTO.getNome(), "Digite seu Nome!");
        Assert.hasText(clienteDTO.getNome(), "Digite seu Nome!");
        Assert.hasText(clienteDTO.getSenha(), "Digite sua Senha!");
        Assert.notNull(clienteDTO.getSenha(), "Digite sua Senha!");
        Assert.hasText(clienteDTO.getEmail(), "Digite seu E-mail!");
        Assert.notNull(clienteDTO.getEmail(), "Digite seu E-mail!");
        Assert.hasText(clienteDTO.getTelefone(), "Digite seu Telefone!");
        Assert.notNull(clienteDTO.getTelefone(), "Digite seu Telefone!");
    }

    public ClienteDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return toClienteDTO(cliente);
    }

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream().map(this::toClienteDTO).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    public String create(ClienteDTO clienteDTO) {
        validationClienteDTO(clienteDTO);
        toClienteDTO(clienteRepository.save(toCliente(clienteDTO)));
        return "Sucesso ao cadastrar novo Registro";
    }

    @Transactional(rollbackFor = Exception.class)
    public String update(Long id, ClienteDTO clienteDTO) {
        idNotNull(id);
        validationClienteDTO(clienteDTO);
        toClienteDTO(clienteRepository.save(toCliente(clienteDTO)));
        return "Sucesso ao atualizar Registro do ID:" + id + " Cliente";
    }

    public void delete(Long id) {
        idNotNull(id);
        clienteRepository.deleteById(id);
    }
}