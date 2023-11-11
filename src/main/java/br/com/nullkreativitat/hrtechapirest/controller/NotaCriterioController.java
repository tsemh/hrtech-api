package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.*;
import br.com.nullkreativitat.hrtechapirest.repository.NotaCriterioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notaCriterio")
public class NotaCriterioController {

    @Autowired
    private NotaCriterioRepository notaCriterioRepository;

    @GetMapping("/lista")
    public List<NotaCriterio> obterTodosCriterios() {
        return notaCriterioRepository.findAll();
    }

    @GetMapping("/pelos-ids/{criterioId}/{processoSeletivoId}/{candidatoId}")
    public ResponseEntity<NotaCriterio> obterPelosIds(
            @PathVariable Long criterioId,
            @PathVariable Long processoSeletivoId,
            @PathVariable Long candidatoId) {
        Optional<NotaCriterio> notaCriterioOptional = notaCriterioRepository.findByCriterio_IdAndProcessoSeletivo_IdAndCandidato_Id(criterioId, processoSeletivoId, candidatoId);

        return notaCriterioOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<NotaCriterio> createNotaCriterio(@RequestBody NotaCriterio notaCriterio) {
        NotaCriterio savedNotaCriterio = notaCriterioRepository.save(notaCriterio);
        return new ResponseEntity<>(savedNotaCriterio, HttpStatus.CREATED);
    }
    
    @GetMapping("/byProcessoSeletivo/{processoSeletivoId}")
    public List<NotaCriterio> getNotasCriteriosByProcessoSeletivo(@PathVariable Long processoSeletivoId) {
        return notaCriterioRepository.findByProcessoSeletivo_Id(processoSeletivoId);
    }

    @GetMapping("/byCandidato/{candidatoId}")
    public List<NotaCriterio> getNotasCriteriosByCandidato(@PathVariable Long candidatoId) {
        return notaCriterioRepository.findByCandidato_Id(candidatoId);
    }

    @GetMapping("/byCriterio/{criterioId}")
    public List<NotaCriterio> getNotasCriteriosByCriterio(@PathVariable Long criterioId) {
        return notaCriterioRepository.findByCriterio_Id(criterioId);
    }

    @GetMapping("/byValor")
    public List<NotaCriterio> getNotasCriteriosByValor(@RequestParam Integer valor) {
        return notaCriterioRepository.findByValor(valor);
    }
}

