package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Cargo;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import br.com.nullkreativitat.hrtechapirest.repository.CargoRepository;
import br.com.nullkreativitat.hrtechapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @PostMapping("/postar")
    public ResponseEntity<Usuario> postarUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(usuario.getId());

        if (!existingUsuario.isPresent()) {
            Usuario savedUsuario = usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/pelo-nome")
    public ResponseEntity<List<Usuario>> obterPeloNome(@RequestParam String nome) {
        List<Usuario> usuarios = usuarioRepository.findByNome(nome);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/pelo-email")
    public ResponseEntity<List<Usuario>> obterPeloEmail(@RequestParam String email) {
        List<Usuario> usuarios = usuarioRepository.findByEmail(email);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/pelo-cargo")
    public ResponseEntity<List<Usuario>> obterPeloCargo(@RequestParam Long cargoId) {
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        if (cargo.isPresent()) {
            List<Usuario> usuarios = usuarioRepository.findByCargo(cargo.get());
            return ResponseEntity.ok(usuarios);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            Usuario usuarioAtualizado = usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuarioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
