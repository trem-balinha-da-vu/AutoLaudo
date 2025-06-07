package com.sistema.autolaudo.dto.permissao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioPermissaoRequest {

    @JsonProperty("usuario_id")
    private Long usuarioId;

    @JsonProperty("permissao_id")
    private Long permissaoId;
}
