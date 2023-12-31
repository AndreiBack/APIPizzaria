package vander.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vander.pizzaria.dto.FuncionarioDTO;
import vander.pizzaria.entity.Funcionario;
import vander.pizzaria.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
    

    public Funcionario create(FuncionarioDTO funcionarioDTO) {
        validateFuncionarioDTO(funcionarioDTO);

        Funcionario funcionario = convertToEntity(funcionarioDTO);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Long id, FuncionarioDTO funcionarioDTO) {
        validateFuncionarioDTO(funcionarioDTO);

        Optional<Funcionario> existingFuncionario = funcionarioRepository.findById(id);
        if (existingFuncionario.isPresent()) {
            Funcionario funcionarioToUpdate = existingFuncionario.get();

            funcionarioToUpdate.setNome(funcionarioDTO.getNome());
            funcionarioToUpdate.setIdade(funcionarioDTO.getIdade());
            funcionarioToUpdate.setCpf(funcionarioDTO.getCpf());
            funcionarioToUpdate.setEmail(funcionarioDTO.getEmail());
            funcionarioToUpdate.setSenha(funcionarioDTO.getSenha());
            funcionarioToUpdate.setTelefone(funcionarioDTO.getTelefone());

            return funcionarioRepository.save(funcionarioToUpdate);
        }
        return null;
    }

    public boolean delete(Long id) {
        Optional<Funcionario> existingFuncionario = funcionarioRepository.findById(id);
        if (existingFuncionario.isPresent()) {
            funcionarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validateFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
        Assert.notNull(funcionarioDTO, "Funcionário não pode ser nulo");
        Assert.isTrue(!funcionarioDTO.getNome().isBlank(), "Nome inválido!");
        Assert.isTrue(funcionarioDTO.getIdade() > 0, "Idade inválida!");
        Assert.isTrue(!funcionarioDTO.getCpf().isBlank(), "cpf inválido!");
        Assert.isTrue(!funcionarioDTO.getTelefone().isBlank(), "telefone inválido!");    }

    private Funcionario convertToEntity(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioDTO.getId());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setIdade(funcionarioDTO.getIdade());
        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setSenha(funcionarioDTO.getSenha());
        funcionario.setTelefone(funcionarioDTO.getTelefone());
        return funcionario;
    }


    public Funcionario findById(final Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    }

    public List<FuncionarioDTO> findAll() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(this::convertToDTO).toList();
    }

    private FuncionarioDTO convertToDTO(Funcionario funcionario) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNome(funcionario.getNome());
        funcionarioDTO.setIdade(funcionario.getIdade());
        funcionarioDTO.setCpf(funcionario.getCpf());
        funcionarioDTO.setEmail(funcionario.getEmail());
        funcionarioDTO.setSenha(funcionario.getSenha());
        funcionarioDTO.setTelefone(funcionario.getTelefone());
        return funcionarioDTO;
    }
}
