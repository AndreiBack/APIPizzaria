package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.DTO.FuncionarioDTO;
import vander.pizzaria.Entity.Funcionario;
import vander.pizzaria.Repository.FuncionarioRepository;
import vander.pizzaria.Service.FuncionarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class FuncionarioTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario());
        funcionarios.add(new Funcionario());
        when(funcionarioRepository.findAll()).thenReturn(funcionarios);

        List<FuncionarioDTO> result = funcionarioService.findAll();
        assert result.size() == funcionarios.size();
    }

    @Test
    public void testCreateFuncionario() {
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
    }

    @Test
    public void testUpdateFuncionario() {
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
    }


    @Test
    public void testDeleteFuncionario() {
        Long id = 1L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(new Funcionario()));
        boolean result = funcionarioService.delete(id);
        assert result;
    }
}
