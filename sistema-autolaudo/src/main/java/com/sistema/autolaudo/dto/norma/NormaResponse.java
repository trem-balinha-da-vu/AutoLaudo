package com.sistema.autolaudo.dto.norma;

import lombok.Data;

@Data
public class NormaResponse {
    private Long idNorma;
    private String nomeNorma;
    private String tipo;
    private String textoNorma;
}

