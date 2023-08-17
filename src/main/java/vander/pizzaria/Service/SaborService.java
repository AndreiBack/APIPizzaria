package vander.pizzaria.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vander.pizzaria.Entity.Sabor;
import vander.pizzaria.Repository.SaborRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SaborService {

    private final SaborRepository saborRepository;

    @Autowired
    public SaborService(SaborRepository saborRepository) {
        this.saborRepository = saborRepository;
    }

    public List<Sabor> getAllSabores() {
        return saborRepository.findAll();
    }

    public Sabor createSabor(Sabor sabor) {
        return saborRepository.save(sabor);
    }

    public Sabor updateSabor(Long id, Sabor sabor) {
        Optional<Sabor> existingSabor = saborRepository.findById(id);
        if (existingSabor.isPresent()) {
            sabor.setId(id);
            return saborRepository.save(sabor);
        }
        return null;
    }

    public boolean deleteSabor(Long id) {
        Optional<Sabor> existingSabor = saborRepository.findById(id);
        if (existingSabor.isPresent()) {
            saborRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
