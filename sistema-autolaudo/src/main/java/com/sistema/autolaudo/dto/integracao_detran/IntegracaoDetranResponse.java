package com.sistema.autolaudo.dto.integracao_detran;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IntegracaoDetranResponse {

    @JsonProperty("id_integracao")
    private Long idIntegracao;

    @JsonProperty("api")
    private String api;

    @JsonProperty("data_chamada")
    private java.time.LocalDateTime dataChamada;

    @JsonProperty("resultado")
    private String resultado;

    @JsonProperty("placa_veiculo")
    private String placaVeiculo;
}

