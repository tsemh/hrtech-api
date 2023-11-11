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
@Table(name="tb_processo_seletivo")
public class ProcessoSeletivo {
    @Id
    @Column(name="id_processo_seletivo")
    @SequenceGenerator(name="processo_seletivo", sequenceName="sq_tb_processo_seletivo", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="processo_seletivo")
    private Long id;
    @Column(name="nm_processo_seletivo")
    private String nome;
    @Column(name="dt_inicio")
    private Date dataInicio;
    @Column(name="dt_fim")
    private Date dataFim;
    @Column(name="nr_vagas")
    private Integer numeroVagas;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    @JsonBackReference(value="processos_seletivos")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="id_cargo")
    @JsonBackReference(value="processos_seletivos")
    private Cargo cargo;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_processoSeletivo", fetch=FetchType.LAZY)
    private List<CandidatoProcesso> candidatosProcesso = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_processoSeletivo", fetch=FetchType.LAZY)
    private List<Criterio> criterios = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_processoSeletivo", fetch=FetchType.LAZY)
    private List<NotaCriterio> notasCriterios = new ArrayList<>();
    public ProcessoSeletivo() {
    }

    public ProcessoSeletivo(Long id, String nome, Date dataInicio, Date dataFim, Integer numeroVagas, Usuario usuario, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.numeroVagas = numeroVagas;
        this.usuario = usuario;
        this.cargo = cargo;
    }
}
