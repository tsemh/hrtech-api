package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import br.com.nullkreativitat.hrtechapirest.entity.Vale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ValeRepository extends JpaRepository<Vale, Long> {
    List<Vale> findByNome(String nome);
    List<Vale> findByValor(Float valor);
    List<Vale> findByData(Date data);
    List<Vale> findByUsuario(Usuario usuario);
}
