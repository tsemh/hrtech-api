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
    private Float valor;

    @ManyToOne
    @JoinColumn(name="id_holerite")
    @JsonBackReference("holerite")
    private Holerite holerite;

    public Reajuste() {
    }

    public Reajuste(Long id, String nome, Float valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }
}
