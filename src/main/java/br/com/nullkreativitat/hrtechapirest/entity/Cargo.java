package br.com.nullkreativitat.hrtechapirest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="tb_cargo")
public class Cargo {
    @Id
    @Column(name="id_cargo")
    @SequenceGenerator(name="cargo", sequenceName="sq_tb_cargo", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cargo")
    private Long id;
    @Column(name="nm_cargo")
    private String nome;
    @Column(name="nv_cargo")
    private Integer nivel;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_cargo", fetch=FetchType.LAZY)
    private List<ProcessoSeletivo> processosSeletivos = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_cargo", fetch=FetchType.LAZY)
    private List<Usuario> usuarios = new ArrayList<>();
    public Cargo() {
    }

    public Cargo(long id, String nome, Integer nivel) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
    }
}
