package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.NotaCriterio;
import br.com.nullkreativitat.hrtechapirest.entity.NotaCriterioKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotaCriterioRepository extends JpaRepository<NotaCriterio, NotaCriterioKey> {
    Optional<NotaCriterio> findByCriterio_IdAndProcessoSeletivo_IdAndCandidato_Id(Long criterioId, Long processoSeletivoId, Long candidatoId);
    List<NotaCriterio> findByCriterio_Id(Long criterioId);
    List<NotaCriterio> findByProcessoSeletivo_Id(Long processoSeletivoId);
    List<NotaCriterio> findByCandidato_Id(Long candidatoId);
    List<NotaCriterio> findByValor(Integer valor);
}
