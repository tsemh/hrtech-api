package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Candidato;
import br.com.nullkreativitat.hrtechapirest.entity.FeedBack;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import br.com.nullkreativitat.hrtechapirest.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @PostMapping("/postar")
    public ResponseEntity<Candidato> createCandidato(@RequestBody Candidato candidato) {
        Candidato savedCandidato = candidatoRepository.save(candidato);
        return new ResponseEntity<>(savedCandidato, HttpStatus.CREATED);
    }
    @GetMapping("/lista")
    public List<Candidato> obterTodosCandidatos() {
        return candidatoRepository.findAll();
    }
    @GetMapping("/pelo-id/{id}")
    public ResponseEntity<Candidato> obterPeloId(@PathVariable Long id) {
        Optional<Candidato> candidato = candidatoRepository.findById(id);
        return candidato.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/pelo-noome")
    public List<Candidato> obterPeloNome(@RequestParam String nome) {
        return candidatoRepository.findByNome(nome);
    }
    @GetMapping("/pelo-email")
    public List<Candidato> obterPeloEmail(@RequestParam String email) {
        return candidatoRepository.findByEmail(email);
    }
    @GetMapping("/pelo-telefone")
    public List<Candidato> obterPeloTelefone(@RequestParam String telefone) {
        return candidatoRepository.findByTelefone(telefone);
    }
    @GetMapping("/pelo-sexo")
    public List<Candidato> obterPeloSexo(@RequestParam String sexo) {
        return candidatoRepository.findBySexo(sexo);
    }
    @GetMapping("/pela-DataNascimento")
    public List<Candidato> obterPelaDataNascimento(@RequestParam Date dataNascimento) {
        return candidatoRepository.findByDataNascimento(dataNascimento);
    }
    @GetMapping("/pelo-feedback")
    public List<Candidato> obterPeloFeedback(@RequestParam Long feedbackId) {
        FeedBack feedback = new FeedBack();
        feedback.setId(feedbackId);
        return candidatoRepository.findByFeedBack(feedback);
    }
    @GetMapping("/pelo-usuario")
    public List<Candidato> obterPeloUsuario(@RequestParam Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        return candidatoRepository.findByUsuario(usuario);
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<Candidato> editarCandidato(@PathVariable Long id, @RequestBody Candidato candidato) {
        if (!candidatoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        candidato.setId(id);
        Candidato updatedCandidato = candidatoRepository.save(candidato);

        return new ResponseEntity<>(updatedCandidato, HttpStatus.OK);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCandidato(@PathVariable Long id) {
        if (!candidatoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        candidatoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
