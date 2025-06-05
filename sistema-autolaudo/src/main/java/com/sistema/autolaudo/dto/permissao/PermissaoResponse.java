package com.sistema.autolaudo.dto.permissao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoResponse {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome_permissao")
    private String nomePermissao;


}
