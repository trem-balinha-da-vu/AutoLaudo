package com.sistema.autolaudo.service.norma;

import com.sistema.autolaudo.dto.norma.NormaRequest;
import com.sistema.autolaudo.dto.norma.NormaResponse;

import java.util.List;

public interface NormaService {
    NormaResponse criarNorma(NormaRequest request);
    NormaResponse buscarNormaPorId(Long id);
    List<NormaResponse> listarNormas();
    NormaResponse atualizarNorma(Long id, NormaRequest request);
    void deletarNorma(Long id);
}
