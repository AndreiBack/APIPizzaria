package vander.pizzaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.Entity.Endereco;
import vander.pizzaria.Repository.EnderecoRepository;
import vander.pizzaria.Service.EnderecoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class EnderecoTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(id);
        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));
        Endereco result = enderecoService.findById(id);
        assert result.getId().equals(id);
    }

    @Test
    public void testFindAll() {
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco());
        enderecos.add(new Endereco());
        when(enderecoRepository.findAll()).thenReturn(enderecos);
        List<Endereco> result = enderecoService.findAll();
        assert result.size() == enderecos.size();
    }

    @Test
    public void testCreate() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345-678");
        endereco.setRua("Rua da Amostra");
        endereco.setBairro("Bairro do Teste");
        endereco.setNumero(123);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        enderecoService.create(endereco);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setCep("12345-678");
        endereco.setRua("Rua da Amostra");
        endereco.setBairro("Bairro do Teste");
        endereco.setNumero(123);
        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));
        enderecoService.update(id, endereco);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(id);
        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));
        enderecoService.delete(id);
    }
}