package vander.pizzaria;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import vander.pizzaria.dto.SaborDTO;
import vander.pizzaria.entity.Sabor;
import vander.pizzaria.repository.SaborRepository;
import vander.pizzaria.service.SaborService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
 class SaborControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @InjectMocks
    private SaborService saborService;

    @Mock
    private SaborRepository saborRepository;
    @BeforeEach
    public void setup() {
    }

    @Test
     void testCreateSabor() throws Exception {
        SaborDTO saborDTO = new SaborDTO();
        saborDTO.setNome("Sabor de Teste");
        saborDTO.setIngredientes(List.of("Ingrediente 1", "Ingrediente 2"));

        String saborJson = objectMapper.writeValueAsString(saborDTO);

        ResultActions result = mockMvc.perform(post("/sabores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saborJson));

        result.andExpect(status().isCreated());
    }
    @Test
     void testGetAllSabores() throws Exception {
        ResultActions result = mockMvc.perform(get("/sabores"));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
     void testDeleteSaborService() {
        Long id = 1L;

        when(saborRepository.existsById(id)).thenReturn(true);
        when(saborRepository.findById(id)).thenReturn(Optional.of(new Sabor()));

        boolean result = saborService.delete(id);

        assert result;
       verify(saborRepository, times(0)).save(any(Sabor.class));


    }
}


