package br.com.nullkreativitat.hrtechapirest.record;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaCriterioDTO {
    private Long criterioId;
    private Long processoSeletivoId;
    private Long candidatoId;
    private Integer valor;

    public NotaCriterioDTO() {
    }

    public NotaCriterioDTO(Long criterioId, Long processoSeletivoId, Long candidatoId, Integer valor) {
        this.criterioId = criterioId;
        this.processoSeletivoId = processoSeletivoId;
        this.candidatoId = candidatoId;
        this.valor = valor;
    }
}