package br.com.nullkreativitat.hrtechapirest.record;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidatoProcessoDTO {
    private Long processoSeletivoId;
    private Long candidatoId;
    private String status;

    public CandidatoProcessoDTO() {
    }

    public CandidatoProcessoDTO(Long processoSeletivoId, Long candidatoId, String status) {
        this.processoSeletivoId = processoSeletivoId;
        this.candidatoId = candidatoId;
        this.status = status;
    }
}
