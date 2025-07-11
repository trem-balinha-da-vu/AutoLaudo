package com.sistema.autolaudo.dto.selodigital;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SeloDigitalResponse {

    @JsonProperty("id_selo")
    private Long idSelo;

    @JsonProperty("data_emissao")
    private java.time.LocalDateTime dataEmissao;

    @JsonProperty("autoridade_emissora")
    private String autoridadeEmissora;

    @JsonProperty("hash_conformidade")
    private String hashConformidade;
}
