package vander.pizzaria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vander.pizzaria.DTO.FuncionarioDTO;
import vander.pizzaria.Entity.Funcionario;
import vander.pizzaria.Service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionario(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.findById(id);
        return new ResponseEntity<>(funcionario, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> getAllFuncionarios() {
        List<FuncionarioDTO> funcionarios = funcionarioService.findAll();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.create(funcionarioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFuncionario(@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.update(id, funcionarioDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        funcionarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
