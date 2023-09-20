package vander.pizzaria.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vander.pizzaria.DTO.PizzaDTO;
import vander.pizzaria.DTO.SaborDTO;
import vander.pizzaria.Entity.Pizza;
import vander.pizzaria.Entity.Sabor;
import vander.pizzaria.Repository.PizzaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaDTO> getAll() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PizzaDTO create(PizzaDTO pizzaDTO) {
        Pizza pizza = convertToEntity(pizzaDTO);
        if (!quantidadeSabor(pizza)) {
            throw new IllegalArgumentException("Número inválido de sabores para o tamanho da pizza.");
        }
        Assert.notNull(pizza.getValor(), "Valor nao pode ser nulo");

        Pizza createdPizza = pizzaRepository.save(pizza);
        return convertToDTO(createdPizza);
    }

    public PizzaDTO update(Long id, PizzaDTO pizzaDTO) {
        Optional<Pizza> existingPizza = pizzaRepository.findById(id);
        if (existingPizza.isPresent()) {
            Pizza pizzaToUpdate = existingPizza.get();
            pizzaToUpdate.setTamanho(pizzaDTO.getTamanho());
            pizzaToUpdate.setSabores(convertDTOsToSabores(pizzaDTO.getSabores()));

            if (!quantidadeSabor(pizzaToUpdate)) {
                throw new IllegalArgumentException("Número inválido de sabores para o tamanho da pizza.");
            }

            Pizza updatedPizza = pizzaRepository.save(pizzaToUpdate);
            return convertToDTO(updatedPizza);
        }
        return null;
    }

    public boolean delete(Long id) {
        Optional<Pizza> existingPizza = pizzaRepository.findById(id);
        if (existingPizza.isPresent()) {
            pizzaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean quantidadeSabor(Pizza pizza) {
        String tamanho = pizza.getTamanho();
        List<Sabor> sabores = pizza.getSabores();

        if (tamanho.equalsIgnoreCase("P") && sabores.size() != 1) {
            return false;
        } else if (tamanho.equalsIgnoreCase("M") && sabores.size() >= 2) {
            return false;
        } else if (tamanho.equalsIgnoreCase("G") && sabores.size() >= 3) {
            return false;
        } else if (tamanho.equalsIgnoreCase("GG") && sabores.size() >= 4) {
            return false;
        }
        return true;
    }

    private PizzaDTO convertToDTO(Pizza pizza) {
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(pizza.getId());
        pizzaDTO.setTamanho(pizza.getTamanho());
        pizzaDTO.setSabores(convertSaboresToDTOs(pizza.getSabores()));
        return pizzaDTO;
    }

    private List<PizzaDTO> convertToDTOs(List<Pizza> pizzas) {
        return pizzas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Pizza convertToEntity(PizzaDTO pizzaDTO) {
        Pizza pizza = new Pizza();
        pizza.setId(pizzaDTO.getId());
        pizza.setTamanho(pizzaDTO.getTamanho());
        pizza.setSabores(convertDTOsToSabores(pizzaDTO.getSabores()));
        return pizza;
    }

    private List<Sabor> convertDTOsToSabores(List<SaborDTO> saborDTOs) {
        return saborDTOs.stream()
                .map(this::convertToSabor)
                .collect(Collectors.toList());
    }

    private List<SaborDTO> convertSaboresToDTOs(List<Sabor> sabores) {
        return sabores.stream()
                .map(this::convertToSaborDTO)
                .collect(Collectors.toList());
    }

    private Sabor convertToSabor(SaborDTO saborDTO) {
        Sabor sabor = new Sabor();
        sabor.setId(saborDTO.getId());
        sabor.setNome(saborDTO.getNome());
        sabor.setIngredientes(saborDTO.getIngredientes());
        return sabor;
    }

    private SaborDTO convertToSaborDTO(Sabor sabor) {
        SaborDTO saborDTO = new SaborDTO();
        saborDTO.setId(sabor.getId());
        saborDTO.setNome(sabor.getNome());
        saborDTO.setIngredientes(sabor.getIngredientes());
        return saborDTO;
    }
}
