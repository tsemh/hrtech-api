package br.com.nullkreativitat.hrtechapirest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    private Date date;
    @Column(name="vl_holerite")
    private Float valor;

    public Holerite() {
    }

    public Holerite(Long id, Date date, Float valor) {
        this.id = id;
        this.date = date;
        this.valor = valor;
    }
}
