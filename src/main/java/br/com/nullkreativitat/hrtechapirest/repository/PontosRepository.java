package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.Pontos;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PontosRepository extends JpaRepository<Pontos, Long> {
    List<Pontos> findByData(LocalDateTime data);
    List<Pontos> findByUsuario(Usuario usuario);
}
