package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.Pontos;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PontosRepository extends JpaRepository<Pontos, Long> {
    List<Pontos> findByData(LocalDateTime data);
    List<Pontos> findByUsuario(Usuario usuario);
    Optional<Pontos> findByDataAndUsuario(LocalDateTime data, Usuario usuario);
    List<Pontos> findByDataBetweenAndUsuario(LocalDateTime startOfDay, LocalDateTime endOfDay, Usuario usuario);

}
