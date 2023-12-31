package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Holerite;
import br.com.nullkreativitat.hrtechapirest.entity.Reajuste;
import br.com.nullkreativitat.hrtechapirest.repository.HoleriteRepository;
import br.com.nullkreativitat.hrtechapirest.repository.ReajusteRepository;
import br.com.nullkreativitat.hrtechapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reajuste")
public class ReajusteController {

    @Autowired
    private ReajusteRepository reajusteRepository;

    @Autowired
    private HoleriteRepository holeriteRepository;

    @PostMapping("/postar")
    public Reajuste postarReajuste(@RequestBody Reajuste reajuste) {
        return reajusteRepository.save(reajuste);
    }
    @GetMapping("/lista")
    public List<Reajuste> obterTodosReajustes() {
        return reajusteRepository.findAll();
    }
    @GetMapping("/pelo-id/{id}")
    public ResponseEntity<Reajuste> obterPeloId(@PathVariable Long id) {
        Optional<Reajuste> reajuste = reajusteRepository.findById(id);
        return reajuste.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/pelo-nome")
    public List<Reajuste> obterPeloNome(@RequestParam String nome) {
        return reajusteRepository.findByNome(nome);
    }
    @GetMapping("/pelo-valor")
    public List<Reajuste> obterPeloValor(@RequestParam Float valor) {
        return reajusteRepository.findByValor(valor);
    }
    @GetMapping("/pelo-holerite")
    public List<Reajuste> obterPeloHolerite(@RequestParam Long idHolerite) {
        Optional<Holerite> holerite = holeriteRepository.findById(idHolerite);
        return holerite.map(reajusteRepository::findByHolerite).orElse(Collections.emptyList());
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<Reajuste> editarReajuste(@PathVariable Long id, @RequestBody Reajuste updatedReajuste) {
        Optional<Reajuste> existingReajuste = reajusteRepository.findById(id);
        if (existingReajuste.isPresent()) {
            Reajuste reajuste = existingReajuste.get();
            reajuste.setNome(updatedReajuste.getNome());
            reajuste.setValor(updatedReajuste.getValor());
            reajusteRepository.save(reajuste);
            return ResponseEntity.ok(reajuste);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarReajuste(@PathVariable Long id) {
        Optional<Reajuste> reajuste = reajusteRepository.findById(id);
        if (reajuste.isPresent()) {
            reajusteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
