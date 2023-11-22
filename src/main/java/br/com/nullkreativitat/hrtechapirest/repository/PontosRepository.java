package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.Pontos;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface PontosRepository extends JpaRepository<Pontos, Long> {
    List<Pontos> findByData(LocalDateTime data);
    List<Pontos> findByUsuario(Usuario usuario);
    @Query("SELECT p FROM Pontos p WHERE YEAR(p.data) = YEAR(:data) " +
           "AND MONTH(p.data) = MONTH(:data) " +
           "AND DAY(p.data) = DAY(:data) " +
           "AND HOUR(p.data) = HOUR(:data) " +
           "AND MINUTE(p.data) = MINUTE(:data) " +
           "AND SECOND(p.data) = SECOND(:data) " +
           "AND p.usuario = :usuario")
    List<Pontos> findByDataAndUsuario(@Param("data") LocalDateTime data, @Param("usuario") Usuario usuario);
}
