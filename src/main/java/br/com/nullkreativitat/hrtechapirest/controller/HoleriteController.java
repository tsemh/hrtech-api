package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Holerite;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import br.com.nullkreativitat.hrtechapirest.repository.HoleriteRepository;
import br.com.nullkreativitat.hrtechapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/holerite")
public class HoleriteController {

    @Autowired
    private HoleriteRepository holeriteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/post")
    public Holerite postarHolerite(@RequestBody Holerite holerite) {
        return holeriteRepository.save(holerite);
    }
    @GetMapping("/lista")
    public List<Holerite> obterTodosHolerites() {
        return holeriteRepository.findAll();
    }
    @GetMapping("/pelo-id/{id}")
    public ResponseEntity<Holerite> obterPeloId(@PathVariable Long id) {
        Optional<Holerite> holerite = holeriteRepository.findById(id);
        return holerite.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/pela-data")
    public List<Holerite> obterPelaData(@RequestParam Date data) {
        return holeriteRepository.findByData(data);
    }
    @GetMapping("/pelo-valor")
    public List<Holerite> obterPeloValor(@RequestParam Float valor) {
        return holeriteRepository.findByValor(valor);
    }
    @GetMapping("/pelo-usuario")
    public List<Holerite> obterPeloUsuario(@RequestParam Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        return usuario.map(holeriteRepository::findByUsuario).orElse(Collections.emptyList());
    }
    @GetMapping("/pela-data-usuario")
       public ResponseEntity<Holerite> obterPelaDataEUsuario(@RequestParam("data") String dataStr, @RequestParam("usuarioId") Long usuarioId) {
        try {
            Date data = new Date(dataStr);

            Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);

            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                Optional<Holerite> holerite = holeriteRepository.findByDataAndUsuario(data, usuario);

                return holerite.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException | NumberFormatException e) {
            return ResponseEntity.badRequest().build(); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<Holerite> editarHolerite(@PathVariable Long id, @RequestBody Holerite updatedHolerite) {
        Optional<Holerite> existingHolerite = holeriteRepository.findById(id);
        if (existingHolerite.isPresent()) {
            Holerite holerite = existingHolerite.get();
            holerite.setData(updatedHolerite.getData());
            holerite.setValor(updatedHolerite.getValor());
            holeriteRepository.save(holerite);
            return ResponseEntity.ok(holerite);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarHolerite(@PathVariable Long id) {
        Optional<Holerite> holerite = holeriteRepository.findById(id);
        if (holerite.isPresent()) {
            holeriteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
