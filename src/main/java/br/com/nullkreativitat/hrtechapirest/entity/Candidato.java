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
@Table(name="tb_candidato")
public class Candidato {
    @Id
    @Column(name="id_candidato")
    @SequenceGenerator(name="candidato", sequenceName="sq_tb_candidato", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="candidato")
    private Long id;
    @Column(name="nm_candidato")
    private String nome;
    @Column(name="ds_email")
    private String email;
    @Column(name="nr_telefone")
    private String telefone;
    @Column(name="sx_candidato")
    private String sexo;
    @Column(name="dt_nascimento")
    private Date DataCascimento;
    @OneToOne
    @JoinColumn(name="id_feedback")
    @JsonBackReference(value="candidatos")
    private FeedBack feedBack;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario")
    @JsonBackReference(value="candidatos")
    private Usuario usuario;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_candidato", fetch=FetchType.LAZY)
    private List<Candidato> candidato = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_candidato", fetch=FetchType.LAZY)
    private List<NotaCriterio> notascriterios = new ArrayList<>();
    public Candidato() {
    }

    public Candidato(Long id, String nome, String email, String telefone, String sexo, Date dataCascimento, FeedBack feedBack, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
        DataCascimento = dataCascimento;
        this.feedBack = feedBack;
        this.usuario = usuario;
    }
}
