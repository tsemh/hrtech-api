package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Holerite;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import br.com.nullkreativitat.hrtechapirest.repository.HoleriteRepository;
import br.com.nullkreativitat.hrtechapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.time.YearMonth;
import java.util.*;

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
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build(); 
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }

    @GetMapping("/pelo-mes-ano-usuario")
    public ResponseEntity<Holerite> obterPeloMesAnoEUsuario(@RequestParam("ano") int ano, @RequestParam("mes") int mes,
                                                            @RequestParam("usuarioId") Long usuarioId) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, ano);
            cal.set(Calendar.MONTH, mes - 1);
            cal.set(Calendar.DAY_OF_MONTH, 1);

            Date inicioMes = cal.getTime();

            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);

            Date fimMes = cal.getTime();

            Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);

            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                Holerite holerite = holeriteRepository.findByDataBetweenAndUsuario(inicioMes, fimMes, usuario);

                if (holerite != null) {
                    return ResponseEntity.ok(holerite);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
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
