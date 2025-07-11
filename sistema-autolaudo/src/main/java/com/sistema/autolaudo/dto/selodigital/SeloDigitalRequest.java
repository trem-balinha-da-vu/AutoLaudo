package com.sistema.autolaudo.dto.selodigital;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SeloDigitalRequest {

    @JsonProperty("data_emissao")
    private java.time.LocalDateTime dataEmissao;

    @JsonProperty("autoridade_emissora")
    private String autoridadeEmissora;

    @JsonProperty("hash_conformidade")
    private String hashConformidade;
}
