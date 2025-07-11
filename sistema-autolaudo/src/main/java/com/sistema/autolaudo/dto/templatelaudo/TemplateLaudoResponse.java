package com.sistema.autolaudo.dto.templatelaudo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TemplateLaudoResponse {

    @JsonProperty("id_template")
    private Long idTemplate;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("versao")
    private String versao;
}
