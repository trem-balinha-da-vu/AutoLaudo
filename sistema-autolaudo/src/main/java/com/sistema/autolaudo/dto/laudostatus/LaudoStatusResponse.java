package com.sistema.autolaudo.dto.laudostatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LaudoStatusResponse {
    @JsonProperty("id_status")
    private Long idStatus;

    @JsonProperty("nome")
    private String nome;
}
