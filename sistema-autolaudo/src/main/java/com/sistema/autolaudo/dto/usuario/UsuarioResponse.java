package com.sistema.autolaudo.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sistema.autolaudo.model.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

/**
 * DTO de resposta para entidade Usuario
 */
@Getter
@Setter
public class UsuarioResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("Perfil")
    private String perfilNome;


}
