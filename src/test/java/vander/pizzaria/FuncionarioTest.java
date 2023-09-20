package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.dto.FuncionarioDTO;
import vander.pizzaria.entity.Funcionario;
import vander.pizzaria.repository.FuncionarioRepository;
import vander.pizzaria.service.FuncionarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

 class FuncionarioTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @BeforeEach
    public void setUp(){MockitoAnnotations.openMocks(this);}

    @Test
     void testGetAllFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario());
        funcionarios.add(new Funcionario());
        when(funcionarioRepository.findAll()).thenReturn(funcionarios);

        List<FuncionarioDTO> result = funcionarioService.findAll();
        assert result.size() == funcionarios.size();
       verify(funcionarioRepository, times(0)).save(any(Funcionario.class));

    }

    @Test
     void testCreateFuncionario() {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setNome("Nome do Funcionário");
        funcionarioDTO.setIdade(25);
        funcionarioDTO.setCpf("123.456.789-09");
        funcionarioDTO.setEmail("vander@gmail.com");
        funcionarioDTO.setSenha("senhalegal");
        funcionarioDTO.setTelefone("(45)99834-7219");

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(new Funcionario());

        Funcionario result = funcionarioService.create(funcionarioDTO);
        assert result != null;
       verify(funcionarioRepository, times(1)).save(any(Funcionario.class));

    }

    @Test
     void testUpdateFuncionario() {
        Long id = 1L;
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(id);
        funcionarioDTO.setNome("Nome do Funcionário");
        funcionarioDTO.setIdade(25);
        funcionarioDTO.setCpf("123.456.789-09");
        funcionarioDTO.setEmail("andrei@gmail.com");
        funcionarioDTO.setSenha("senhalegal");
        funcionarioDTO.setTelefone("(45)99834-7219");

        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(new Funcionario()));
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(new Funcionario());

        Funcionario updatedFuncionario = funcionarioService.update(id, funcionarioDTO);
       verify(funcionarioRepository, times(1)).save(any(Funcionario.class));

    }


    @Test
     void testDeleteFuncionario() {
        Long id = 1L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(new Funcionario()));
        boolean result = funcionarioService.delete(id);
        assert result;
       verify(funcionarioRepository, times(0)).save(any(Funcionario.class));

    }
}
