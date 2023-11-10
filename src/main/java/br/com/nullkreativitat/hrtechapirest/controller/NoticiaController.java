package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Noticia;
import br.com.nullkreativitat.hrtechapirest.repository.NoticiaRepository;
import br.com.nullkreativitat.hrtechapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaRepository noticiaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping("/lista")
    public List<Noticia> listarNoticias() {
        return noticiaRepository.findAll();
    }
    @GetMapping("/pelo-id/{id}")
    public ResponseEntity<Noticia> getById(@PathVariable Long id) {
        Optional<Noticia> noticia = noticiaRepository.findById(id);
        return noticia.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/postar")
    public Noticia adicionarNoticia(@RequestBody Noticia noticia) {
        noticia.setDate(new Date());
        return noticiaRepository.save(noticia);
    }

    @GetMapping("/pelo-nome")
    public List<Noticia> buscarPorNome(@RequestParam String nome) {
        return noticiaRepository.findByNome(nome);
    }

    @GetMapping("/pelo-usuario")
    public List<Noticia> buscarPorUsuario(@RequestParam Long idUsuario) {
        return noticiaRepository.findByUsuario(usuarioRepository.findById(idUsuario).orElse(null));
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<Noticia> update(@PathVariable Long id, @RequestBody Noticia novaNoticia) {
        Optional<Noticia> noticia = noticiaRepository.findById(id);

        if (noticia.isPresent()) {
            Noticia noticiaExistente = noticia.get();
            noticiaExistente.setNome(novaNoticia.getNome());
            noticiaExistente.setDescricao(novaNoticia.getDescricao());
            noticiaExistente.setDate(novaNoticia.getDate());
            noticiaExistente.setUsuario(novaNoticia.getUsuario());

            return new ResponseEntity<>(noticiaRepository.save(noticiaExistente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (noticiaRepository.existsById(id)) {
            noticiaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
