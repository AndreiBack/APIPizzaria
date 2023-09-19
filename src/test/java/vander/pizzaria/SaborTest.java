package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.DTO.SaborDTO;
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

        List<SaborDTO> result = saborService.getAll();

        assert result.size() == sabores.size();
    }

    @Test
    public void testCreateSabor() {
        SaborDTO saborDTO = new SaborDTO(null, "Sabor de Teste", List.of("Ingrediente 1", "Ingrediente 2"));
        Sabor sabor = new Sabor();
        sabor.setId(1L);
        sabor.setNome(saborDTO.getNome());
        sabor.setIngredientes(saborDTO.getIngredientes());

        when(saborRepository.save(any(Sabor.class))).thenReturn(sabor);

        SaborDTO result = saborService.create(saborDTO);

        assert result != null;
        assert result.getId().equals(1L);
    }

    @Test
    public void testUpdateSabor() {
        Long id = 1L;
        SaborDTO saborDTO = new SaborDTO(id, "Sabor Atualizado", List.of("Ingrediente 1", "Ingrediente 2"));
        Sabor sabor = new Sabor();
        sabor.setId(id);
        sabor.setNome(saborDTO.getNome());
        sabor.setIngredientes(saborDTO.getIngredientes());

        when(saborRepository.findById(id)).thenReturn(Optional.of(sabor));
        when(saborRepository.save(any(Sabor.class))).thenReturn(sabor);

        SaborDTO updatedSabor = saborService.update(id, saborDTO);

        assert updatedSabor != null;
        assert updatedSabor.getId().equals(id);
        assert updatedSabor.getNome().equals("Sabor Atualizado");
    }

    @Test
    public void testDeleteSabor() {
        Long id = 1L;
        Sabor sabor = new Sabor();
        sabor.setId(id);

        when(saborRepository.findById(id)).thenReturn(Optional.of(sabor));

        boolean result = saborService.delete(id);

        assert result;
    }
}
