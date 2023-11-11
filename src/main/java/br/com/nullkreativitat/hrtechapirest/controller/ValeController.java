package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import br.com.nullkreativitat.hrtechapirest.entity.Vale;
import br.com.nullkreativitat.hrtechapirest.repository.UsuarioRepository;
import br.com.nullkreativitat.hrtechapirest.repository.ValeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vale")
public class ValeController {

    @Autowired
    private ValeRepository valeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public Vale createVale(@RequestBody Vale vale) {
        return valeRepository.save(vale);
    }
    @GetMapping("/lista")
    public List<Vale> getAllVales() {
        return valeRepository.findAll();
    }
    @GetMapping("/pelo-id/{id}")
    public ResponseEntity<Vale> getValeById(@PathVariable Long id) {
        Optional<Vale> vale = valeRepository.findById(id);
        return vale.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/pelo-nome")
    public List<Vale> getValesByNome(@RequestParam String nome) {
        return valeRepository.findByNome(nome);
    }
    @GetMapping("/pelo-valor")
    public List<Vale> getValesByValor(@RequestParam Float valor) {
        return valeRepository.findByValor(valor);
    }
    @GetMapping("/pela-data")
    public List<Vale> getValesByData(@RequestParam Date data) {
        return valeRepository.findByData(data);
    }
    @GetMapping("/pelo-usuario")
    public List<Vale> getValesByUsuario(@RequestParam Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        return usuario.map(valeRepository::findByUsuario).orElse(Collections.emptyList());
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<Vale> updateVale(@PathVariable Long id, @RequestBody Vale updatedVale) {
        Optional<Vale> existingVale = valeRepository.findById(id);
        if (existingVale.isPresent()) {
            Vale vale = existingVale.get();
            vale.setNome(updatedVale.getNome());
            vale.setValor(updatedVale.getValor());
            vale.setData(updatedVale.getData());
            valeRepository.save(vale);
            return ResponseEntity.ok(vale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteVale(@PathVariable Long id) {
        Optional<Vale> vale = valeRepository.findById(id);
        if (vale.isPresent()) {
            valeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
