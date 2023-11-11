package br.com.nullkreativitat.hrtechapirest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_notaCriterio")
public class NotaCriterio {

    @EmbeddedId
    private NotaCriterioKey id;

    @Column(name = "vl_notaCriterio")
    private Integer valor;

    @ManyToOne
    @MapsId("criterio")
    @JoinColumn(name = "id_criterio")
    @JsonBackReference(value = "notasCriterios")
    private Criterio criterio;

    @ManyToOne
    @MapsId("processoSeletivo")
    @JoinColumn(name = "id_processoSeletivo")
    @JsonBackReference(value = "notasCriterios")
    private ProcessoSeletivo processoSeletivo;

    @ManyToOne
    @MapsId("candidatoProcesso")
    @JoinColumn(name = "id_candidato")
    @JsonBackReference(value = "notasCriterios")
    private Candidato candidato;

    public NotaCriterio() {
    }

    public NotaCriterio(NotaCriterioKey id, Integer valor, Criterio criterio, ProcessoSeletivo processoSeletivo, Candidato candidato) {
        this.id = id;
        this.valor = valor;
        this.criterio = criterio;
        this.processoSeletivo = processoSeletivo;
        this.candidato = candidato;
    }

    @Transient
    public Long getCriterioId() {
        return criterio != null ? criterio.getId() : null;
    }

    @Transient
    public Long getProcessoSeletivoId() {
        return processoSeletivo != null ? processoSeletivo.getId() : null;
    }

    @Transient
    public Long getCandidatoId() {
        return candidato != null ? candidato.getId() : null;
    }
}
