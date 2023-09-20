package vander.pizzaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.entity.Endereco;
import vander.pizzaria.repository.EnderecoRepository;
import vander.pizzaria.service.EnderecoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

 class EnderecoTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @BeforeEach
    public void setUp(){MockitoAnnotations.openMocks(this);}

    @Test
     void testFindById() {
        Long id = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(id);
        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));
        Endereco result = enderecoService.findById(id);
        assert result.getId().equals(id);
       verify(enderecoRepository, times(0)).save(any(Endereco.class));

    }

    @Test
     void testFindAll() {
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco());
        enderecos.add(new Endereco());
        when(enderecoRepository.findAll()).thenReturn(enderecos);
        List<Endereco> result = enderecoService.findAll();
        assert result.size() == enderecos.size();
       verify(enderecoRepository, times(0)).save(any(Endereco.class));

    }

    @Test
     void testCreate() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345-678");
        endereco.setRua("Rua da Amostra");
        endereco.setBairro("Bairro do Teste");
        endereco.setNumero(123);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        enderecoService.create(endereco);

       verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
     void testUpdate() {
        Long id = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setCep("12345-678");
        endereco.setRua("Rua da Amostra");
        endereco.setBairro("Bairro do Teste");
        endereco.setNumero(123);
        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));
        enderecoService.update(id, endereco);
       verify(enderecoRepository, times(1)).save(any(Endereco.class));

    }

    @Test
     void testDelete() {
        Long id = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(id);
        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));
        enderecoService.delete(id);
       verify(enderecoRepository, times(0)).save(any(Endereco.class));

    }
}