package br.com.nullkreativitat.hrtechapirest.controller;

import br.com.nullkreativitat.hrtechapirest.entity.Plano;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import br.com.nullkreativitat.hrtechapirest.repository.PlanoRepository;
import br.com.nullkreativitat.hrtechapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/cadastrar")
    public Plano postarPlano(@RequestBody Plano plano, @RequestParam long idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if(usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            plano.setUsuario(usuario);
            return planoRepository.save(plano);
        } else {
            throw new RuntimeException("Usuário não encontrado com o ID "+idUsuario);
        }
    }
    @GetMapping("/obter-lista")
    public List<Plano> obterTodosPlanos() {
        return planoRepository.findAll();
    }
    @GetMapping("/obter-pelo-id/{id}")
    public Plano obterPlanoPeloId(@PathVariable long id) {
        return planoRepository.findById(id).get();
    }
    @GetMapping("/obter-pelo-nome")
    public List<Plano> obterPeloNome(@RequestParam String nome) {
        if(nome == null) {
            throw new RuntimeException("Nome"+nome+"não foi encontrado");
        }
        return planoRepository.findByNome(nome);
    }
    @GetMapping("/obter-pelo-valor")
    public List<Plano> obterPelovalor(@RequestParam Float valor) {
        if(valor == null) {
            throw new RuntimeException("Valor"+valor+"não foi encontrado");
        }
        return planoRepository.findByValor(valor);
    }
    @GetMapping("/obter-pela-validade")
    public List<Plano> obterPelaValidade(@RequestParam Date validade) {
        if(validade == null) {
            throw new RuntimeException("Validade"+validade+"não foi encontrada");
        }
        return planoRepository.findByValidade(validade);
    }
    @GetMapping("/obter-pelo-usuario")
    public List<Plano> obterPeloUsuario(@RequestParam Usuario usuario) {
        if(usuario == null) {
            throw new RuntimeException("Usuário"+usuario+"não foi encontrado");
        }
        return planoRepository.findByUsuario(usuario);
    }
    @PutMapping("/editar/{id}")
    public Plano editarPlano(@RequestBody Plano plano, @PathVariable Long id) {
        plano.setId(id);
        return planoRepository.save(plano);
    }
    @DeleteMapping("deletar/{id}")
    public void deletarPlano(@PathVariable Long id) {
        planoRepository.deleteById(id);
    }
}



























