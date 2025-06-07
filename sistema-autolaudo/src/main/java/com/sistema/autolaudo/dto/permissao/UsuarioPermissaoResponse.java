package com.sistema.autolaudo.dto.permissao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sistema.autolaudo.dto.usuario.UsuarioResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioPermissaoResponse {

    @JsonProperty("usuario")
    private UsuarioResponse usuario;

    @JsonProperty("permissao")
    private PermissaoResponse permissao;

}
