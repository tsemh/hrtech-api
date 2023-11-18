package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.Cargo;
import br.com.nullkreativitat.hrtechapirest.entity.ProcessoSeletivo;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.PropertyEditor;
import java.util.Date;
import java.util.List;

public interface ProcessoSeletivoRepository extends JpaRepository<ProcessoSeletivo, Long> {
    List<ProcessoSeletivo> findByNome(String nome);
    List<ProcessoSeletivo> findByDataInicio(Date dataInicio);
    List<ProcessoSeletivo> findByDataFim(Date dataFim);
    List<ProcessoSeletivo> findByNumeroVagas(Integer numeroVagas);
    List<ProcessoSeletivo> findByUsuario(Usuario usuario);
    List<ProcessoSeletivo> findByCargo(Cargo cargo);
}
