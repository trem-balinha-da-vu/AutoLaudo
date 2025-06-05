package com.sistema.autolaudo.dto.perfil;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilRequest {

    @JsonProperty("nome_perfil")
    private String nomePerfil;
}
