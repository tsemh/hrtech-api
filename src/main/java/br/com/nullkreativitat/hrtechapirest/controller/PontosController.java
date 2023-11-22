package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Pontos;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import br.com.nullkreativitat.hrtechapirest.repository.PontosRepository;
import br.com.nullkreativitat.hrtechapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalTime;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pontos")
public class PontosController {

    @Autowired
    private PontosRepository pontosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/postar")
    public Pontos postarPontos(@RequestBody Pontos ponto) {
        ponto.setData(LocalDateTime.now());
        return pontosRepository.save(ponto);
    }
    @GetMapping("/lista")
    public List<Pontos> obterTodosPontos() {
        return pontosRepository.findAll();
    }
    @GetMapping("/pelo-id/{id}")
    public ResponseEntity<Pontos> obterPeloId(@PathVariable Long id) {
        Optional<Pontos> ponto = pontosRepository.findById(id);
        return ponto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/pela-data")
    public List<Pontos> obterPelaData(@RequestParam LocalDateTime data) {
        return pontosRepository.findByData(data);
    }
    @GetMapping("/pelo-usuario")
    public List<Pontos> obterPeloUsuario(@RequestParam Long idUsuario) {
        return pontosRepository.findByUsuario(usuarioRepository.findById(idUsuario).orElse(null));
    }

    @GetMapping("/pela-data-usuario")
    public ResponseEntity<Pontos> obterPelaDataEUsuario(@RequestParam("data") String dataStr, @RequestParam("usuarioId") Long usuarioId) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
            LocalDateTime data = LocalDateTime.parse(dataStr, formatter);

            Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);

            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                Optional<Pontos> pontos = pontosRepository.findByDataAndUsuario(data, usuario);

                return pontos.map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound().build());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

@GetMapping("/pelo-dia-usuario")
public List<Pontos> obterPontosPeloDiaEUsuario(
        @RequestParam("data") LocalDate data,
        @RequestParam("usuarioId") Long usuarioId) {

    LocalDateTime startOfDay = data.atStartOfDay();
    LocalDateTime endOfDay = data.atTime(LocalTime.MAX); 

    Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);

    if (usuarioOptional.isPresent()) {
        Usuario usuario = usuarioOptional.get();
        return pontosRepository.findByDataBetweenAndUsuario(startOfDay, endOfDay, usuario);
    } else {
        return Collections.emptyList(); 
    }
}


    @PutMapping("editar/{id}")
    public ResponseEntity<Pontos> editarPlano(@PathVariable Long id, @RequestBody Pontos novoPonto) {
        Optional<Pontos> ponto = pontosRepository.findById(id);

        if (ponto.isPresent()) {
            Pontos pontoExistente = ponto.get();
            pontoExistente.setPontp(novoPonto.getPontp());
            pontoExistente.setData(novoPonto.getData());
            pontoExistente.setUsuario(novoPonto.getUsuario());

            return new ResponseEntity<>(pontosRepository.save(pontoExistente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Long id) {
        if (pontosRepository.existsById(id)) {
            pontosRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
