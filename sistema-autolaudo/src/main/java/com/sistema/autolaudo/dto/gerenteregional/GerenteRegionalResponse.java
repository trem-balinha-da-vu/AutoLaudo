package com.sistema.autolaudo.dto.gerenteregional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sistema.autolaudo.dto.perito.PeritoResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GerenteRegionalResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("regional")
    private String regional;

    @JsonProperty("perito")
    private PeritoResponse perito;
}
