package br.com.nullkreativitat.hrtechapirest.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CandidatoProcessoKey implements Serializable {

    @ManyToOne
    private ProcessoSeletivo processoSeletivo;

    @ManyToOne
    private Candidato candidato;

    public CandidatoProcessoKey() {
    }

    public CandidatoProcessoKey(ProcessoSeletivo processoSeletivo, Candidato candidato) {
        this.processoSeletivo = processoSeletivo;
        this.candidato = candidato;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatoProcessoKey that = (CandidatoProcessoKey) o;
        return Objects.equals(processoSeletivo, that.processoSeletivo) && Objects.equals(candidato, that.candidato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processoSeletivo, candidato);
    }
}
