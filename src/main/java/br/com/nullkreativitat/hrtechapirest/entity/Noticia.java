package br.com.nullkreativitat.hrtechapirest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="tb_noticia")
public class Noticia {
    @Id
    @Column(name="id_noticia")
    @SequenceGenerator(name="noticia", sequenceName="sq_tb_noticia", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="noticia")
    private Long id;

    @Column(name="nm_noticia")
    private String nome;

    @Column(name="ds_noticia")
    private String descricao;

    @Column(name="dt_noticia")
    private Date data;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    @JsonBackReference(value="usuario")
    private Usuario usuario;

    public Noticia() {
    }

    public Noticia(Long id, String nome, String descricao, Date data, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.usuario = usuario;
    }
}
