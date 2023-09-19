package vander.pizzaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.Entity.Sabor;
import vander.pizzaria.Repository.SaborRepository;
import vander.pizzaria.Service.SaborService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class SaborTest {

    @Mock
    private SaborRepository saborRepository;

    @InjectMocks
    private SaborService saborService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllSabores() {
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(new Sabor());
        sabores.add(new Sabor());

        when(saborRepository.findAll()).thenReturn(sabores);

        List<Sabor> result = saborService.getAllSabores();

        assert result.size() == sabores.size();
    }

    @Test
    public void testCreateSabor() {
        Sabor sabor = new Sabor();
        sabor.setNome("Sabor de Teste");
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Ingrediente 1");
        ingredientes.add("Ingrediente 2");
        sabor.setIngredientes(ingredientes);

        when(saborRepository.save(any(Sabor.class))).thenReturn(sabor);

        Sabor result = saborService.createSabor(sabor);

        assert result != null;
    }

    @Test
    public void testUpdateSabor() {
        Long id = 1L;
        Sabor sabor = new Sabor();
        sabor.setId(id);
        sabor.setNome("Sabor Atualizado");
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Ingrediente 1");
        ingredientes.add("Ingrediente 2");
        sabor.setIngredientes(ingredientes);

        when(saborRepository.findById(id)).thenReturn(Optional.of(sabor));
        when(saborRepository.save(any(Sabor.class))).thenReturn(sabor);

        Sabor updatedSabor = saborService.updateSabor(id, sabor);

        assert updatedSabor != null;
        assert updatedSabor.getId().equals(id);
    }

    @Test
    public void testDeleteSabor() {
        Long id = 1L;
        Sabor sabor = new Sabor();
        sabor.setId(id);

        when(saborRepository.findById(id)).thenReturn(Optional.of(sabor));

        boolean result = saborService.deleteSabor(id);

        assert result;
    }
}
