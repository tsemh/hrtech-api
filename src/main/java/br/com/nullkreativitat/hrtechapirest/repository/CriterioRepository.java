package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.Criterio;
import br.com.nullkreativitat.hrtechapirest.entity.ProcessoSeletivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriterioRepository extends JpaRepository<Criterio, Long> {
    List<Criterio> findByNome(String nome);
    List<Criterio> findByValor(Integer valor);
    List<Criterio> findByProcessoSeletivo(ProcessoSeletivo processoSeletivo);
}
