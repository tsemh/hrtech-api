package br.com.nullkreativitat.hrtechapirest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="tb_plano")
public class Plano {

    @Id
    @Column(name="id_plano")
    @SequenceGenerator(name="plano", sequenceName="sq_tb_plano", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="plano")
    private long id;

    @Column(name="ds_plano")
    private String descricao;
    @Column(name="nm_plano")
    private String nome;
    @Column(name="vl_plano")
    private float valor;
    @Column(name="dt_validade_plano")
    private Date validade;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    @JsonBackReference(value="planos")
    private Usuario usuario;
}
