package com.sistema.autolaudo.dto.perfil;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sistema.autolaudo.model.perfil.Perfil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("nome_perfil")
    private String nomePerfil;


}
