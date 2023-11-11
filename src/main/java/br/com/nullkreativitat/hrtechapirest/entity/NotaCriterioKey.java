package br.com.nullkreativitat.hrtechapirest.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class NotaCriterioKey implements Serializable {

    @ManyToOne
    private Criterio criterio;

    @ManyToOne
    private ProcessoSeletivo processoSeletivo;

    @ManyToOne
    private Candidato candidato;

    public NotaCriterioKey() {
    }

    public NotaCriterioKey(Criterio criterio, ProcessoSeletivo processoSeletivo, Candidato candidato) {
        this.criterio = criterio;
        this.processoSeletivo = processoSeletivo;
        this.candidato = candidato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotaCriterioKey that = (NotaCriterioKey) o;
        return Objects.equals(criterio, that.criterio) && Objects.equals(processoSeletivo, that.processoSeletivo) && Objects.equals(candidato, that.candidato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(criterio, processoSeletivo, candidato);
    }
}
