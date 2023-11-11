package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.Candidato;
import br.com.nullkreativitat.hrtechapirest.entity.FeedBack;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    List<Candidato> findByNome(String nome);
    List<Candidato> findByEmail(String email);
    List<Candidato> findByTelefone(String telefone);
    List<Candidato> findBySexo(String sexo);
    List<Candidato> findByDataCascimento(Date dataNascimento);
    List<Candidato> findByFeedBack(FeedBack feedBack);
    List<Candidato> findByUsuario(Usuario usuario);
}
