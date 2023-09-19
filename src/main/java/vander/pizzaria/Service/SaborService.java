package vander.pizzaria.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vander.pizzaria.DTO.SaborDTO;
import vander.pizzaria.Entity.Sabor;
import vander.pizzaria.Repository.SaborRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaborService {

    private final SaborRepository saborRepository;

    @Autowired
    public SaborService(SaborRepository saborRepository) {
        this.saborRepository = saborRepository;
    }

    public List<SaborDTO> getAll() {
        List<Sabor> sabores = saborRepository.findAll();
        return sabores.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SaborDTO create(SaborDTO saborDTO) {
        validateSabor(saborDTO);
        Sabor sabor = convertToEntity(saborDTO);
        sabor = saborRepository.save(sabor);
        return convertToDTO(sabor);
    }

    public SaborDTO update(Long id, SaborDTO saborDTO) {
        Optional<Sabor> existingSabor = saborRepository.findById(id);
        if (existingSabor.isPresent()) {
            Sabor sabor = convertToEntity(saborDTO);
            sabor.setId(id);
            sabor = saborRepository.save(sabor);
            return convertToDTO(sabor);
        }
        return null;
    }

    public boolean delete(Long id) {
        Optional<Sabor> existingSabor = saborRepository.findById(id);
        if (existingSabor.isPresent()) {
            saborRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private SaborDTO convertToDTO(Sabor sabor) {
        return new SaborDTO(sabor.getId(), sabor.getNome(), sabor.getIngredientes());
    }

    private Sabor convertToEntity(SaborDTO saborDTO) {
        Sabor sabor = new Sabor();
        sabor.setNome(saborDTO.getNome());
        sabor.setIngredientes(saborDTO.getIngredientes());
        return sabor;
    }

    private void validateSabor(SaborDTO saborDTO) {
        Assert.notNull(saborDTO.getNome(), "Nome não pode ser nulo!");
        Assert.isTrue(!saborDTO.getNome().isBlank(), "Nome inválido!");

        Assert.notNull(saborDTO.getIngredientes(), "Ingredientes não podem ser nulos!");

    }
}
