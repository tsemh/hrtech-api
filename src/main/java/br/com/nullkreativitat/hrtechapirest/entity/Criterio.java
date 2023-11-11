package br.com.nullkreativitat.hrtechapirest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_criterio")
public class Criterio {
    @Id
    @Column(name="id_criterio")
    @SequenceGenerator(name="criterio", sequenceName="sq_tb_criterio", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="criterio")
    private Long id;
    @Column(name="nm_criterio")
    private String nome;
    @Column(name="vl_criterio")
    private Integer valor;
    @ManyToOne
    @JoinColumn(name="id_processoSeletivo")
    @JsonBackReference(value="criterios")
    private ProcessoSeletivo processoSeletivo;

    public Criterio() {
    }

    public Criterio(Long id, String nome, Integer valor, ProcessoSeletivo processoSeletivo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.processoSeletivo = processoSeletivo;
    }
}
