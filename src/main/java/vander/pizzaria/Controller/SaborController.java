package vander.pizzaria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vander.pizzaria.Entity.Sabor;
import vander.pizzaria.Service.SaborService;

import java.util.List;

@RestController
@RequestMapping("/sabores")
public class SaborController {

    private final SaborService saborService;

    @Autowired
    public SaborController(SaborService saborService) {
        this.saborService = saborService;
    }

    @GetMapping
    public ResponseEntity<List<Sabor>> getAllSabores() {
        List<Sabor> sabores = saborService.getAllSabores();
        return new ResponseEntity<>(sabores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Sabor> createSabor(@RequestBody Sabor sabor) {
        Sabor createdSabor = saborService.createSabor(sabor);
        return new ResponseEntity<>(createdSabor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sabor> updateSabor(@PathVariable Long id, @RequestBody Sabor sabor) {
        Sabor updatedSabor = saborService.updateSabor(id, sabor);
        if (updatedSabor != null) {
            return new ResponseEntity<>(updatedSabor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSabor(@PathVariable Long id) {
        boolean deleted = saborService.deleteSabor(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
