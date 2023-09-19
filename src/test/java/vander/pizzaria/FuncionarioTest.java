package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
        List<Funcionario> result = funcionarioService.getAllFuncionarios();
        assert result.size() == funcionarios.size();
    }

    @Test
    public void testCreateFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Nome do Funcionário");
        funcionario.setIdade(30);
        funcionario.setCpf("12345678901");
        funcionario.setEmail("funcionario@example.com");
        funcionario.setSenha("senha123");
        funcionario.setTelefone("123-456-7890");
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);
        Funcionario result = funcionarioService.createFuncionario(funcionario);
        assert result != null;
    }

    @Test
    public void testUpdateFuncionario() {
        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome("Nome do Funcionário");
        funcionario.setIdade(30);
        funcionario.setCpf("12345678901");
        funcionario.setEmail("funcionario@example.com");
        funcionario.setSenha("senha123");
        funcionario.setTelefone("123-456-7890");
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);
        Funcionario updatedFuncionario = funcionarioService.updateFuncionario(id, funcionario);
        assert updatedFuncionario != null;
        assert updatedFuncionario.getId().equals(id);
    }

    @Test
    public void testDeleteFuncionario() {
        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));
        boolean result = funcionarioService.deleteFuncionario(id);
        assert result;
    }
}
