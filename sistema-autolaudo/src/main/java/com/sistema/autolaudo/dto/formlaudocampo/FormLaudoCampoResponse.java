package com.sistema.autolaudo.dto.formlaudocampo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FormLaudoCampoResponse {

    @JsonProperty("id_formulario")
    private Long idFormulario;

    @JsonProperty("nome_campo")
    private String nomeCampo;

    @JsonProperty("valor")
    private String valor;
}
