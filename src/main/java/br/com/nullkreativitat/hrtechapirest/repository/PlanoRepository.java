package br.com.nullkreativitat.hrtechapirest.repository;

import br.com.nullkreativitat.hrtechapirest.entity.Plano;
import br.com.nullkreativitat.hrtechapirest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PlanoRepository extends JpaRepository<Plano, Long> {

    List<Plano> findByNome(String nome);
    List<Plano> fyndByValor(float valor);
    List<Plano> findByValidade(Date validade);
    List<Plano> findByUsuario(Usuario usuario);
}
