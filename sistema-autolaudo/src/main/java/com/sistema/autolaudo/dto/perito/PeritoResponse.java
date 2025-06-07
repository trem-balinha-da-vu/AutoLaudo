package com.sistema.autolaudo.dto.perito;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sistema.autolaudo.dto.usuario.UsuarioResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeritoResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("crm_perito")
    private String crmPerito;

    @JsonProperty("area_especialidade")
    private String areaEspecialidade;

    @JsonProperty("nivel_experiencia")
    private String nivelExperiencia;

    @JsonProperty("telefone_contato")
    private String telefoneContato;

    @JsonProperty("ativo")
    private Boolean ativo;

    @JsonProperty("usuario")
    private UsuarioResponse usuario;
}
