package com.sistema.autolaudo.dto.laudoveicular;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LaudoVeicularResponse {
    @JsonProperty("id_laudo")
    private Long idLaudo;

    @JsonProperty("data_criacao")
    private LocalDateTime dataCriacao;

    @JsonProperty("data_entrega")
    private LocalDateTime dataEntrega;

    @JsonProperty("data_emissao")
    private LocalDateTime dataEmissao;

    @JsonProperty("status")
    private String status;

    @JsonProperty("orgao_requisitante")
    private String orgaoRequisitante;

    @JsonProperty("resumo_problema")
    private String resumoProblema;

    @JsonProperty("detalhamento_analises")
    private String detalhamentoAnalises;

    @JsonProperty("conclusao")
    private String conclusao;

    @JsonProperty("observacoes")
    private String observacoes;

    @JsonProperty("id_perito")
    private Long idPerito;

    @JsonProperty("placa_veiculo")
    private String placaVeiculo;

    @JsonProperty("normas")
    private List<String> normas; // Nomes das normas aplicadas
}
