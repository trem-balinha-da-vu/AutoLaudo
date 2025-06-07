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
    private Long perfilId; // 1 = Perito, 2 = Gerente Regional, 3 = Administrador

    // Para administrador (opcional)
    @JsonProperty("nivelAcessoId")
    private Long nivelAcessoId; // 1 = TOTAL, 2 = PARCIAL

    // Para gerente_regional, exemplo:
    @JsonProperty("regional")
    private String regional;

    // Para perito:
    @JsonProperty("crmPerito")
    private String crmPerito;

    @JsonProperty("areaEspecialidade")
    private String areaEspecialidade;

    @JsonProperty("nivelExperiencia")
    private String nivelExperiencia;

    @JsonProperty("telefoneContato")
    private String telefoneContato;
}
