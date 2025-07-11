package com.sistema.autolaudo.dto.sincronizador;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SincronizadorRequest {
    @JsonProperty("id_laudo")
    private Long idLaudo;

    @JsonProperty("id_usuario")
    private Long idUsuario;

    @JsonProperty("detalhamento_analises")
    private String detalhamentoAnalises;

    @JsonProperty("conclusao")
    private String conclusao;

    @JsonProperty("observacoes")
    private String observacoes;

    @JsonProperty("data_sync")
    private LocalDateTime dataSync;
}
