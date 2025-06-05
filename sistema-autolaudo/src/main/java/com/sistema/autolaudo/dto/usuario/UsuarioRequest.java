package com.sistema.autolaudo.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Payload para criação/atualização de usuário.
 */
@Getter
@Setter
public class UsuarioRequest {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("senha")
    private String senha;

    @JsonProperty("perfilId")
    private Long perfilId;
}
