package com.sistema.autolaudo.dto.integracao_detran;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IntegracaoDetranRequest {

    @JsonProperty("chassi")
    private String chassi;
}
