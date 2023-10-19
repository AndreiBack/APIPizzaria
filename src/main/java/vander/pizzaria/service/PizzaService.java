package vander.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vander.pizzaria.dto.PizzaDTO;
import vander.pizzaria.dto.SaborDTO;
import vander.pizzaria.entity.Pizza;
import vander.pizzaria.entity.Sabor;
import vander.pizzaria.repository.PizzaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaDTO> getAll() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas.stream().map(this::convertToDTO).toList();
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
            pizzaToUpdate.setValor(pizzaDTO.getValor());
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
        int quantidadeSabores = sabores.size();

        switch (tamanho.toUpperCase()) {
            case "P":
                return quantidadeSabores == 1;
            case "M":
                return quantidadeSabores <= 2;
            case "G":
                return quantidadeSabores <= 3;
            case "GG":
                return quantidadeSabores <= 4;
            default:
                return false;
        }
    }


    private PizzaDTO convertToDTO(Pizza pizza) {
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(pizza.getId());
        pizzaDTO.setTamanho(pizza.getTamanho());
        pizzaDTO.setValor(pizza.getValor());
        pizzaDTO.setSabores(convertSaboresToDTOs(pizza.getSabores()));
        return pizzaDTO;
    }



    public Pizza convertToEntity(PizzaDTO pizzaDTO) {
        Pizza pizza = new Pizza();
        pizza.setId(pizzaDTO.getId());
        pizza.setTamanho(pizzaDTO.getTamanho());
        pizza.setValor(pizzaDTO.getValor());
        pizza.setSabores(convertDTOsToSabores(pizzaDTO.getSabores()));
        return pizza;
    }

    private List<Sabor> convertDTOsToSabores(List<SaborDTO> saborDTOs) {
        return saborDTOs.stream()
                .map(this::convertToSabor)
                .toList();
    }


    private List<SaborDTO> convertSaboresToDTOs(List<Sabor> sabores) {
        return sabores.stream()
                .map(this::convertToSaborDTO)
                .toList();
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
