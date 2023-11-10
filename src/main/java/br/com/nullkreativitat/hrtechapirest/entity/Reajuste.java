package br.com.nullkreativitat.hrtechapirest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_reajuste")
public class Reajuste {
    @Id
    @Column(name="id_reajuste")
    @SequenceGenerator(name="reajuste", sequenceName="sq_tb_reajuste", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="reajuste")
    private Long id;
    @Column(name="nm_reajuste")
    private String nome;
    @Column(name="vl_reajuste")
    private Float reajuste;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    @JsonBackReference(value="reajustes")
    private Usuario usuario;

    public Reajuste() {
    }

    public Reajuste(Long id, String nome, Float reajuste, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.reajuste = reajuste;
        this.usuario = usuario;
    }
}
