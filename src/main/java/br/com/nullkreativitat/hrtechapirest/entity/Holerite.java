package br.com.nullkreativitat.hrtechapirest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="tb_holerite")
public class Holerite {
    @Id
    @Column(name="id_holerite")
    @SequenceGenerator(name="holerite", sequenceName="sq_tb_holerite", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="holerite")
    private Long id;
    @Column(name="dt_holerite")
    private Date data;
    @Column(name="vl_holerite")
    private Float valor;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    @JsonBackReference(value="holerites")
    private Usuario usuario;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_holerite", fetch=FetchType.LAZY)
    private List<Reajuste> reajustes = new ArrayList<>();
    public Holerite() {
    }

    public Holerite(Long id, Date data, Float valor) {
        this.id = id;
        this.data = data;
        this.valor = valor;
    }
}
