package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;
import vander.pizzaria.dto.ClienteDTO;
import vander.pizzaria.entity.Cliente;
import vander.pizzaria.repository.ClienteRepository;
import vander.pizzaria.service.ClienteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
 class ClienteTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp(){MockitoAnnotations.openMocks(this);}

    @Test
     void testFindById() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        ClienteDTO result = clienteService.findById(id);
        Assert.isTrue(result.getId().equals(id), "Cliente encontrado incorretamente");
       verify(clienteRepository, times(0)).save(any(Cliente.class));

    }

    @Test
     void testFindAll() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente());
        clientes.add(new Cliente());
        when(clienteRepository.findAll()).thenReturn(clientes);
        List<ClienteDTO> result = clienteService.findAll();
        Assert.isTrue(result.size() == clientes.size(), "Número incorreto de clientes encontrados");
       verify(clienteRepository, times(0)).save(any(Cliente.class));

    }

    @Test
     void testCreate() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("obrabo");
        clienteDTO.setIdade(30);
        clienteDTO.setCpf("123.456.789-09");
        clienteDTO.setEmail("andrei@gmail.com");
        clienteDTO.setSenha("senha123");
        clienteDTO.setTelefone("(45)99834-7219");

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSenha(clienteDTO.getSenha());
        cliente.setTelefone(clienteDTO.getTelefone());

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        clienteService.create(clienteDTO);
       verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
     void testUpdate() {
        Long id = 1L;
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(id);
        clienteDTO.setNome("vanderlei");
        clienteDTO.setIdade(30);
        clienteDTO.setCpf("128.876.789-99");
        clienteDTO.setEmail("andrei@gmail.com");
        clienteDTO.setSenha("senha123");
        clienteDTO.setTelefone("(45)99834-7219");

        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSenha(clienteDTO.getSenha());
        cliente.setTelefone(clienteDTO.getTelefone());

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        clienteService.update(id, clienteDTO);
       verify(clienteRepository, times(1)).save(any(Cliente.class));

    }

    @Test
     void testDelete() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        clienteService.delete(id);
       verify(clienteRepository, times(0)).save(any(Cliente.class));

    }

    @Test
     void testSetters() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("obrabo");
        clienteDTO.setIdade(30);
        clienteDTO.setCpf("123.456.789-09");
        clienteDTO.setEmail("andrei@gmail.com");
        clienteDTO.setSenha("senha123");
        clienteDTO.setTelefone("(45)99834-7219");

        Cliente cliente = new Cliente();
        cliente.setId(1L);

        cliente.setNome(clienteDTO.getNome());
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSenha(clienteDTO.getSenha());
        cliente.setTelefone(clienteDTO.getTelefone());


        assertEquals("obrabo", cliente.getNome());
        assertEquals(30, cliente.getIdade());
        assertEquals("123.456.789-09", cliente.getCpf());
        assertEquals("andrei@gmail.com", cliente.getEmail());
        assertEquals("senha123", cliente.getSenha());
        assertEquals("(45)99834-7219", cliente.getTelefone());
    }


}