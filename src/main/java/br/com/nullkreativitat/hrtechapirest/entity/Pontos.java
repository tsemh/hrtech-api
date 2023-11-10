package br.com.nullkreativitat.hrtechapirest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="tb_pontos")
public class Pontos {
    @Id
    @Column(name="id_pontos")
    @SequenceGenerator(name="pontos", sequenceName="sq_tb_pontos", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pontos")
    private Long id;
    @Column(name="ds_ponto")
    private String pontp;
    @Column(name="dt_ponto")
    private LocalDateTime data;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    @JsonBackReference(value="pontos")
    private Usuario usuario;
}
