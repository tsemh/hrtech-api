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
@Table(name="tb_feedback")
public class FeedBack {
    @Id
    @Column(name="id_feedback")
    @SequenceGenerator(name="feedback", sequenceName="sq_tb_feedback", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="feedback")
    private Long id;
    @Column(name="ds_feedback")
    private String descricao;
    @Column(name="nm_feedback")
    private String nm_feedback;
    @Column(name="dt_feedback")
    private Date data;
    @Column(name="st_feedback")
    private String status;
    @OneToOne
    @JoinColumn(name="id_usuario")
    @JsonBackReference(value="feedback")
    private Usuario usuario;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="id_feedback", fetch=FetchType.LAZY)
    private List<Candidato> candidatos = new ArrayList<>();

    public FeedBack() {
    }

    public FeedBack(Long id, String descricao, String nm_feedback, Date data, String status, Usuario usuario) {
        this.id = id;
        this.descricao = descricao;
        this.nm_feedback = nm_feedback;
        this.data = data;
        this.status = status;
        this.usuario = usuario;
    }
}
