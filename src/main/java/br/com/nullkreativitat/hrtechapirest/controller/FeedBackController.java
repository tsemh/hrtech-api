package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.FeedBack;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import br.com.nullkreativitat.hrtechapirest.repository.FeedBackRepository;
import br.com.nullkreativitat.hrtechapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/feedback")
public class FeedBackController {
    @Autowired
    private FeedBackRepository feedBackRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/postar")
    public ResponseEntity<FeedBack> postarFeedback(@RequestBody FeedBack feedback) {
        FeedBack savedFeedback = feedBackRepository.save(feedback);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<FeedBack>> obterTodosFeedbacks() {
        List<FeedBack> feedbacks = feedBackRepository.findAll();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/pelo-id/{id}")
    public ResponseEntity<FeedBack> obterPeloId(@PathVariable Long id) {
        Optional<FeedBack> feedback = feedBackRepository.findById(id);
        return feedback.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/pelo-nome/{nome}")
    public ResponseEntity<List<FeedBack>> obterPeloNome(@RequestParam String nome) {
        List<FeedBack> feedbacks = feedBackRepository.findBYNome(nome);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/pela-data/{data}")
    public ResponseEntity<List<FeedBack>> obterPelaData(@RequestParam Date data) {
        List<FeedBack> feedbacks = feedBackRepository.findByData(data);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
    @GetMapping("/pelo-status/{status}")
    public ResponseEntity<List<FeedBack>> obterPeloStatus(@RequestParam String status) {
        List<FeedBack> feedbacks = feedBackRepository.findByStatus(status);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
    @GetMapping("/pelo-usuario/{idUsuario}")
    public ResponseEntity<List<FeedBack>> obterPeloUsuario(@RequestParam Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()) {
            List<FeedBack> feedbacks = feedBackRepository.findBYUsuario(usuario.get());
            return new ResponseEntity<>(feedbacks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<FeedBack> editarFeedback(@PathVariable Long id, @RequestBody FeedBack updatedFeedback) {
        if (!feedBackRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedFeedback.setId(id);
        FeedBack savedFeedback = feedBackRepository.save(updatedFeedback);
        return new ResponseEntity<>(savedFeedback, HttpStatus.OK);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarFeedback(@PathVariable Long id) {
        if (!feedBackRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        feedBackRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
