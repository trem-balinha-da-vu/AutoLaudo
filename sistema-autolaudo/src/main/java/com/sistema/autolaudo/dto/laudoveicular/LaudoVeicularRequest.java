package com.sistema.autolaudo.dto.laudoveicular;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LaudoVeicularRequest {
    @JsonProperty("data_entrega")
    private LocalDateTime dataEntrega;

    @JsonProperty("id_orgao")
    private Long idOrgao;

    @JsonProperty("placa_veiculo")
    private String placaVeiculo;

    @JsonProperty("id_perito")
    private Long idPerito;

    @JsonProperty("resumo_problema")
    private String resumoProblema;

    @JsonProperty("id_normas")
    private List<Long> idNormas;

    @JsonProperty("id_formulario")
    private Long idFormulario;
}
