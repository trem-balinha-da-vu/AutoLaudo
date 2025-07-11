package com.sistema.autolaudo.dto.veiculo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VeiculoRequest {
    @JsonProperty("placa")
    private String placa;

    @JsonProperty("chassi")
    private String chassi;

    @JsonProperty("renavam")
    private String renavam;

    @JsonProperty("modelo")
    private String modelo;

    @JsonProperty("ano_fabricacao")
    private Integer anoFabricacao;

    @JsonProperty("status_registro")
    private String statusRegistro;
}
