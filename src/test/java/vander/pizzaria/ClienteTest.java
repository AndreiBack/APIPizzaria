package vander.pizzaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;
import vander.pizzaria.Entity.Cliente;
import vander.pizzaria.Repository.ClienteRepository;
import vander.pizzaria.Service.ClienteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class ClienteTest {

    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        Cliente result = clienteService.findById(id);
        Assert.isTrue(result.getId().equals(id), "Cliente encontrado incorretamente");
    }

    @Test
    public void testFindAll() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente());
        clientes.add(new Cliente());
        when(clienteRepository.findAll()).thenReturn(clientes);
        List<Cliente> result = clienteService.findAll();
        Assert.isTrue(result.size() == clientes.size(), "Número incorreto de clientes encontrados");
    }
    @Test
    public void testCreate() {
        Cliente cliente = new Cliente();
        cliente.setNome("obrabo");
        cliente.setIdade(30);
        cliente.setCpf("123.456.789-09");
        cliente.setEmail("cliente@example.com");
        cliente.setSenha("senha123");
        cliente.setTelefone("(45)99834-7219");
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        clienteService.create(cliente);
    }


    @Test
    public void testUpdate() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome("vanderlei");
        cliente.setIdade(30);
        cliente.setCpf("128.876.789-99");
        cliente.setEmail("cliente@example.com");
        cliente.setSenha("senha123");
        cliente.setTelefone("(45)99834-7219");
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        clienteService.update(id, cliente);
    }


    @Test
    public void testDelete() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        clienteService.delete(id);
    }

}
