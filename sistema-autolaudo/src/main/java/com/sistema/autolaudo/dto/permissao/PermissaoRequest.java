package com.sistema.autolaudo.dto.permissao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoRequest {

    @JsonProperty("nome_permissao")
    private String nomePermissao;
}
