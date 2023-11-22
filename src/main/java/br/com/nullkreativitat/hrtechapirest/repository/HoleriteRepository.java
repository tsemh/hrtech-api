package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.Holerite;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HoleriteRepository extends JpaRepository<Holerite, Long> {
    List<Holerite> findByData(Date data);
    List<Holerite> findByValor(Float valor);
    List<Holerite> findByUsuario(Usuario usuario);
    Optional<Holerite> findByDataAndUsuario(Date data, Usuario usuario);
    Holerite findByDataBetweenAndUsuario(Date inicioMes, Date fimMes, Usuario usuario);

}