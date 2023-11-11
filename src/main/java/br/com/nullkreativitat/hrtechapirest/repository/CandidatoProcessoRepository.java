package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidatoProcessoRepository extends JpaRepository<CandidatoProcesso, CandidatoProcessoKey> {
    Optional<CandidatoProcesso> findByCandidato_IdAndProcessoSeletivo_Id(Long candidatoId, Long processoSeletivoId);
    List<CandidatoProcesso> findByCandidato_Id(Long candidatoId);
    List<CandidatoProcesso> findByProcessoSeletivo_Id(Long processoSeletivoId);
    List<CandidatoProcesso> findByStatus(String status);
}
