package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
    List<Cargo> findByNome(String nome);
    List<Cargo> findByNivel(Integer nivel);
    @Query("SELECT c FROM Cargo c WHERE c.nome = :nome AND c.nivel = :nivel")
    Cargo findByNomeAndNivel(@Param("nome") String nome, @Param("nivel") Integer nivel);
}
