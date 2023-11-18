package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Cargo;
import br.com.nullkreativitat.hrtechapirest.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargo")
public class CargoController {
    @Autowired
    private CargoRepository cargoRepository;

    @PostMapping("/postar")
    public ResponseEntity<Cargo> criarCargo(@RequestBody Cargo cargo) {
        Cargo novoCargo = cargoRepository.save(cargo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCargo);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Cargo> > obterTodosCargos() {
        List<Cargo> cargos = cargoRepository.findAll();
        return ResponseEntity.ok(cargos);
    }

    @GetMapping("/pelo-id/{id}")
    public ResponseEntity<Cargo> obterCargoPorId(@PathVariable Long id) {
        Optional<Cargo> cargoOptional = cargoRepository.findById(id);

        if (cargoOptional.isPresent()) {
            return ResponseEntity.ok(cargoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pelo-nome/{nome}")
    public ResponseEntity<List<Cargo>> obterPeloNome(@RequestParam String nome) {
        List<Cargo> cargos = cargoRepository.findByNome(nome);
        return ResponseEntity.ok(cargos);
    }

    @GetMapping("/pelo-nivel/{nivel}")
    public ResponseEntity<List<Cargo>> obterPeloNivel(@RequestParam Integer nivel) {
        List<Cargo> cargos = cargoRepository.findByNivel(nivel);
        return ResponseEntity.ok(cargos);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Cargo> editarCargo(@PathVariable Long id, @RequestBody Cargo cargo) {
        if (cargoRepository.existsById(id)) {
            cargo.setId(id);
            Cargo cargoAtualizado = cargoRepository.save(cargo);
            return ResponseEntity.ok(cargoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCargo(@PathVariable Long id) {
        if (cargoRepository.existsById(id)) {
            cargoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
