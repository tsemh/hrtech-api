package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.Holerite;
import br.com.nullkreativitat.hrtechapirest.entity.Reajuste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReajusteRepository extends JpaRepository<Reajuste, Long> {
    List<Reajuste> findByNome(String nome);
    List<Reajuste> findByValor(Float valor);
    List<Reajuste> findByHolerite(Holerite holerite);
}
