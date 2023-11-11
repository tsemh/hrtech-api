package br.com.nullkreativitat.hrtechapirest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="tb_usuario")
public class Usuario {
    @Id
    @Column(name="id_usuario")
    @SequenceGenerator(name="usuario", sequenceName="sq_tb_usuario", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario")
    private Long id;
    @Column(name="nm_usuario")
    private String nome;
    @Column(name="ds_email")
    private String email;
    @Column(name="sn_usuario")
    private String senha;
    @ManyToOne
    @JoinColumn(name="id_cargo")
    @JsonBackReference("cargos")
    private Cargo cargo;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Candidato candidato;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_usuario", fetch=FetchType.LAZY)
    private List<FeedBack> feedbacks = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_usuario", fetch=FetchType.LAZY)
    private List<Holerite> holerites = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_usuario", fetch=FetchType.LAZY)
    private List<Plano> planos = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_usuario", fetch=FetchType.LAZY)
    private List<Pontos> pontos = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_usuario", fetch=FetchType.LAZY)
    private List<ProcessoSeletivo> processosSeletivos = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_usuario", fetch=FetchType.LAZY)
    private List<Vale> vales = new ArrayList<>();
    public Usuario() {
    }

    public Usuario(long id, String nome, String email, String senha, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }
}
