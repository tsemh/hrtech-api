package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.CandidatoProcesso;
import br.com.nullkreativitat.hrtechapirest.record.CandidatoProcessoDTO;
import br.com.nullkreativitat.hrtechapirest.repository.CandidatoProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidatoProcesso")
public class CandidatoProcessoController {

    @Autowired
    private CandidatoProcessoRepository candidatoProcessoRepository;

    @PostMapping("/postar")
    public ResponseEntity<CandidatoProcesso> postCandidatoProcesso(@RequestBody CandidatoProcesso candidatoProcesso) {
        CandidatoProcesso savedCandidatoProcesso = candidatoProcessoRepository.save(candidatoProcesso);
        return new ResponseEntity<>(savedCandidatoProcesso, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public List<CandidatoProcessoDTO> obterTodosCandidatosProcesso() {
        List<CandidatoProcesso> candidatosProcesso = candidatoProcessoRepository.findAll();
        return candidatosProcesso.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/pelos-ids")
    public ResponseEntity<CandidatoProcessoDTO> obterPelosIds(@RequestParam Long candidatoId, @RequestParam Long processoSeletivoId) {
        Optional<CandidatoProcesso> candidatoProcessoOptional = candidatoProcessoRepository.findByCandidato_IdAndProcessoSeletivo_Id(candidatoId, processoSeletivoId);
        return candidatoProcessoOptional.map(value -> {
            CandidatoProcessoDTO dto = convertToDTO(value);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/pelo-candidato")
    public List<CandidatoProcesso> obterPeloCandidato(@RequestParam Long candidatoId) {
        return candidatoProcessoRepository.findByCandidato_Id(candidatoId);
    }

    @GetMapping("/pelo-processoSeletivo")
    public List<CandidatoProcesso> obterPeloProcessoSeletivo(@RequestParam Long processoSeletivoId) {
        return candidatoProcessoRepository.findByProcessoSeletivo_Id(processoSeletivoId);
    }

    @GetMapping("/pelo-status")
    public List<CandidatoProcesso> obterPeloStatus(@RequestParam String status) {
        return candidatoProcessoRepository.findByStatus(status);
    }

    private CandidatoProcessoDTO convertToDTO(CandidatoProcesso candidatoProcesso) {
        CandidatoProcessoDTO dto = new CandidatoProcessoDTO();
        dto.setProcessoSeletivoId(candidatoProcesso.getProcessoSeletivoId());
        dto.setCandidatoId(candidatoProcesso.getCandidatoId());
        dto.setStatus(candidatoProcesso.getStatus());
        return dto;
    }
}
