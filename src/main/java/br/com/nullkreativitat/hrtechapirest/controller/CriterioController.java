package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Criterio;
import br.com.nullkreativitat.hrtechapirest.entity.ProcessoSeletivo;
import br.com.nullkreativitat.hrtechapirest.repository.CriterioRepository;
import br.com.nullkreativitat.hrtechapirest.repository.ProcessoSeletivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/criterio")
public class CriterioController {
    @Autowired
    private CriterioRepository criterioRepository;
    @Autowired
    private ProcessoSeletivoRepository processoSeletivoRepository;

    @PostMapping("/postar")
    public ResponseEntity<Criterio> postarCriterio(@RequestBody Criterio criterio) {
        Criterio savedCriterio = criterioRepository.save(criterio);
        return new ResponseEntity<>(savedCriterio, HttpStatus.CREATED);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Criterio>> obterTodosCriterios() {
        List<Criterio> criterios = criterioRepository.findAll();
        return new ResponseEntity<>(criterios, HttpStatus.OK);
    }
    @GetMapping("/pelo-id/{id}")
    public ResponseEntity<Criterio> obterPeloId(@PathVariable Long id) {
        Optional<Criterio> criterio = criterioRepository.findById(id);
        return criterio.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/pelo-nome")
    public ResponseEntity<List<Criterio>> obterPeloNome(@RequestParam String nome) {
        List<Criterio> criterios = criterioRepository.findByNome(nome);
        return new ResponseEntity<>(criterios, HttpStatus.OK);
    }
    @GetMapping("/pelo-valor")
    public ResponseEntity<List<Criterio>> obterPeloValor(@RequestParam Integer valor) {
        List<Criterio> criterios = criterioRepository.findByValor(valor);
        return new ResponseEntity<>(criterios, HttpStatus.OK);
    }
    @GetMapping("/pelo-processoSeletivo")
    public ResponseEntity<List<Criterio>> obterPeloProcessoSeletivo(@RequestParam Long idProcessoSeletivo) {
        Optional<ProcessoSeletivo> processoSeletivo = processoSeletivoRepository.findById(idProcessoSeletivo);

        if (processoSeletivo.isPresent()) {
            List<Criterio> criterios = criterioRepository.findByProcessoSeletivo(processoSeletivo.get());
            return new ResponseEntity<>(criterios, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/editar-{id}")
    public ResponseEntity<Criterio> editarCriterio(@PathVariable Long id, @RequestBody Criterio updatedCriterio) {
        if (!criterioRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedCriterio.setId(id);
        Criterio savedCriterio = criterioRepository.save(updatedCriterio);
        return new ResponseEntity<>(savedCriterio, HttpStatus.OK);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCriterio(@PathVariable Long id) {
        if (!criterioRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        criterioRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
