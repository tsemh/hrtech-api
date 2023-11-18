package br.com.nullkreativitat.hrtechapirest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_candidatoProcesso")
public class CandidatoProcesso {
    @EmbeddedId
    private CandidatoProcessoKey id;

    @Column(name = "st_candidato")
    private String status;

    @ManyToOne
    @MapsId("processoSeletivo")
    @JoinColumn(name = "id_candidato")
    @JsonBackReference(value = "processoSeletivo")
    private ProcessoSeletivo processoSeletivo;

    @ManyToOne
    @MapsId("candidatoProcesso")
    @JoinColumn(name = "id_candidato")
    @JsonBackReference(value = "candidato")
    private Candidato candidato;

    public CandidatoProcesso() {
    }

    public CandidatoProcesso(CandidatoProcessoKey id, String status, ProcessoSeletivo processoSeletivo, Candidato candidato) {
        this.id = id;
        this.status = status;
        this.processoSeletivo = processoSeletivo;
        this.candidato = candidato;
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
