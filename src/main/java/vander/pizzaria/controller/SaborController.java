package vander.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vander.pizzaria.dto.SaborDTO;
import vander.pizzaria.service.SaborService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sabores")
public class SaborController {

    private final SaborService saborService;

    @Autowired
    public SaborController(SaborService saborService) {
        this.saborService = saborService;
    }

    @GetMapping
    public ResponseEntity<List<SaborDTO>> getAllSabores() {
        List<SaborDTO> sabores = saborService.getAll();
        return new ResponseEntity<>(sabores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaborDTO> createSabor(@RequestBody SaborDTO saborDTO) {
        SaborDTO createdSabor = saborService.create(saborDTO);
        return new ResponseEntity<>(createdSabor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaborDTO> updateSabor(@PathVariable Long id, @RequestBody SaborDTO saborDTO) {
        SaborDTO updatedSabor = saborService.update(id, saborDTO);
        if (updatedSabor != null) {
            return new ResponseEntity<>(updatedSabor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSabor(@PathVariable Long id) {
        boolean deleted = saborService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
