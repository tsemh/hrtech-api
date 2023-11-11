package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.FeedBack;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {
    List<FeedBack> findBYNome(String nome);
    List<FeedBack> findByData(Date data);
    List<FeedBack> findByStatus(String status);
    List<FeedBack> findBYUsuario(Usuario usuario);
}
