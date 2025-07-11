package com.sistema.autolaudo.dto.formulariolaudo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class FormularioLaudoRequest {
    @JsonProperty("id_template")
    private Long idTemplate;

    @JsonProperty("modo_offline")
    private Boolean modoOffline;

    @JsonProperty("campos")
    private Map<String, String> campos;
}
