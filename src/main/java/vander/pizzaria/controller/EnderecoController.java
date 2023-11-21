package vander.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vander.pizzaria.dto.EnderecoDTO;
import vander.pizzaria.service.EnderecoService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService service;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> getEnderecoById(@PathVariable Long id) {
        EnderecoDTO enderecoDTO = service.findById(id);
        return ResponseEntity.ok(enderecoDTO);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> getAllEnderecos() {
        List<EnderecoDTO> enderecoDTOs = service.findAll();
        return ResponseEntity.ok(enderecoDTOs);
    }

    @PostMapping
    public ResponseEntity<String> createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        String responseMessage = service.create(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        String responseMessage = service.update(id, enderecoDTO);
        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("erro")
    private ResponseEntity<List<EnderecoDTO>> exemploErro(){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}