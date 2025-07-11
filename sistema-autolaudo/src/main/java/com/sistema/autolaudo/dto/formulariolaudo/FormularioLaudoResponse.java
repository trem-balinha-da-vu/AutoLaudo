package com.sistema.autolaudo.dto.formulariolaudo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class FormularioLaudoResponse {

    @JsonProperty("id_formulario")
    private Long idFormulario;

    @JsonProperty("modo_offline")
    private Boolean modoOffline;

    @JsonProperty("id_template")
    private Long idTemplate;

    @JsonProperty("nome_template")
    private String nomeTemplate; // Facilita exibição no front

    @JsonProperty("campos")
    private Map<String, String> campos;
}
