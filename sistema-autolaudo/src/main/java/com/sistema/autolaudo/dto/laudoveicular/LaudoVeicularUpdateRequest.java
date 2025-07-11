package com.sistema.autolaudo.dto.laudoveicular;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LaudoVeicularUpdateRequest {
    @JsonProperty("detalhamento_analises")
    private String detalhamentoAnalises;

    @JsonProperty("conclusao")
    private String conclusao;

    @JsonProperty("observacoes")
    private String observacoes;

    @JsonProperty("id_status")
    private Long idStatus; // Quando for concluir ou alterar status
}

