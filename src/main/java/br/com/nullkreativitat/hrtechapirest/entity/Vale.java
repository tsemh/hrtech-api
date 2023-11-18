package br.com.nullkreativitat.hrtechapirest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="tb_vale")
public class Vale {
    @Id
    @Column(name="id_vale")
    @SequenceGenerator(name="vale", sequenceName="sq_tb_vale", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="vale")
    private Long id;

    @Column(name="nm_vale")
    private String nome;

    @Column(name="vl_vale")
    private Float valor;

    @Column(name="dt_vale")
    private Date data;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    @JsonBackReference(value="usuario")
    private Usuario usuario;

    public Vale() {
    }
    public Vale(Long id, String nome, Float valor, Date data, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.usuario = usuario;
    }
}
