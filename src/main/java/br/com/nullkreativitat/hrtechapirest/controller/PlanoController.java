package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Plano;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import br.com.nullkreativitat.hrtechapirest.repository.PlanoRepository;
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
@RequestMapping("/plano")
public class PlanoController {

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/postar")
    public ResponseEntity<Plano> postarPlano(@RequestBody Plano plano, @RequestParam long idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            plano.setUsuario(usuario);
            Plano novoPlano = planoRepository.save(plano);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPlano);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Plano>> obterTodosPlanos() {
        List<Plano> planos = planoRepository.findAll();
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/pelo-id/{id}")
    public ResponseEntity<Plano> obterPlanoPeloId(@PathVariable long id) {
        Optional<Plano> planoOptional = planoRepository.findById(id);
        if (planoOptional.isPresent()) {
            return ResponseEntity.ok(planoOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/pelo-nome")
    public ResponseEntity<List<Plano>> obterPeloNome(@RequestParam String nome) {
        if (nome == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        List<Plano> planos = planoRepository.findByNome(nome);
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/pelo-valor")
    public ResponseEntity<List<Plano>> obterPeloValor(@RequestParam Float valor) {
        if (valor == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        List<Plano> planos = planoRepository.findByValor(valor);
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/pela-validade")
    public ResponseEntity<List<Plano>> obterPelaValidade(@RequestParam Date validade) {
        if (validade == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        List<Plano> planos = planoRepository.findByValidade(validade);
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/pelo-usuario")
    public ResponseEntity<List<Plano>> obterPeloUsuario(@RequestParam Long usuarioId) {
        if (usuarioId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            List<Plano> planos = planoRepository.findByUsuario(usuario);
            return ResponseEntity.ok(planos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Plano> editarPlano(@RequestBody Plano plano, @PathVariable Long id) {
        Optional<Plano> existingPlano = planoRepository.findById(id);
        if (existingPlano.isPresent()) {
            plano.setId(id);
            Plano planoAtualizado = planoRepository.save(plano);
            return ResponseEntity.ok(planoAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Long id) {
        if (planoRepository.existsById(id)) {
            planoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
