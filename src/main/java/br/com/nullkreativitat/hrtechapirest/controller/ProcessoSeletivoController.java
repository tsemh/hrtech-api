package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Cargo;
import br.com.nullkreativitat.hrtechapirest.entity.ProcessoSeletivo;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import br.com.nullkreativitat.hrtechapirest.repository.CargoRepository;
import br.com.nullkreativitat.hrtechapirest.repository.ProcessoSeletivoRepository;
import br.com.nullkreativitat.hrtechapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditor;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/processoSeletivo")
public class ProcessoSeletivoController {

    @Autowired
    private ProcessoSeletivoRepository processoSeletivoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CargoRepository cargoRepository;
    @PostMapping("/postar")
    public ResponseEntity<ProcessoSeletivo> createProcessoSeletivo(@RequestBody ProcessoSeletivo processoSeletivo) {
        ProcessoSeletivo savedProcessoSeletivo = processoSeletivoRepository.save(processoSeletivo);
        return new ResponseEntity<>(savedProcessoSeletivo, HttpStatus.CREATED);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<ProcessoSeletivo>> obterTodosProcessosSeletivos() {
        List<ProcessoSeletivo> processosSeletivos = processoSeletivoRepository.findAll();
        return new ResponseEntity<>(processosSeletivos, HttpStatus.OK);
    }

    @GetMapping("/pelo-id/{id}")
    public ResponseEntity<ProcessoSeletivo> obterPeloId(@PathVariable Long id) {
        Optional<ProcessoSeletivo> processoSeletivo = processoSeletivoRepository.findById(id);
        return processoSeletivo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/pelo-nome")
    public ResponseEntity<List<ProcessoSeletivo>> obterPeloNome(@RequestParam String nome) {
        List<ProcessoSeletivo> processosSeletivos = processoSeletivoRepository.findByNome(nome);
        return new ResponseEntity<>(processosSeletivos, HttpStatus.OK);
    }
    @GetMapping("/pela-dataInicio")
    public ResponseEntity<List<ProcessoSeletivo>> obterPelaDataInicio(@RequestParam Date dataInicio) {
        List<ProcessoSeletivo> processosSeletivos = processoSeletivoRepository.findByDataInicio(dataInicio);
        return new ResponseEntity<>(processosSeletivos, HttpStatus.OK);
    }
    @GetMapping("/pela-dataFim")
    public ResponseEntity<List<ProcessoSeletivo>> obterPelaDataFim(@RequestParam Date dataFim) {
        List<ProcessoSeletivo> processosSeletivos = processoSeletivoRepository.findByDataFim(dataFim);
        return new ResponseEntity<>(processosSeletivos, HttpStatus.OK);
    }
    @GetMapping("/pelo-numeroVagas")
    public ResponseEntity<List<ProcessoSeletivo>> obterPeloNumeroVagas(@RequestParam Integer numeroVagas) {
        List<ProcessoSeletivo> processosSeletivos = processoSeletivoRepository.findByNumeroVagas(numeroVagas);
        return new ResponseEntity<>(processosSeletivos, HttpStatus.OK);
    }
    @GetMapping("/pelo-usuario")
    public ResponseEntity<List<ProcessoSeletivo>> obterPeloUsuario(@RequestParam Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()) {
            List<ProcessoSeletivo> processosSeletivos = processoSeletivoRepository.findByUsuario(usuario.get());
            return new ResponseEntity<>(processosSeletivos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/pelo-cargo")
    public ResponseEntity<List<ProcessoSeletivo>> obterPeloCargo(@RequestParam Long idCargo) {
        Optional<Cargo> cargo = cargoRepository.findById(idCargo);

        if (cargo.isPresent()) {
            List<ProcessoSeletivo> processosSeletivos = processoSeletivoRepository.findByCargo(cargo.get());
            return new ResponseEntity<>(processosSeletivos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<ProcessoSeletivo> editarProcessoSeletivo(@PathVariable Long id, @RequestBody ProcessoSeletivo updatedProcessoSeletivo) {
        if (!processoSeletivoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedProcessoSeletivo.setId(id);
        ProcessoSeletivo savedProcessoSeletivo = processoSeletivoRepository.save(updatedProcessoSeletivo);
        return new ResponseEntity<>(savedProcessoSeletivo, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProcessoSeletivo(@PathVariable Long id) {
        if (!processoSeletivoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        processoSeletivoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
