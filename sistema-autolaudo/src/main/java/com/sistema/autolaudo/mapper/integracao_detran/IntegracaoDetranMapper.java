package com.sistema.autolaudo.mapper.integracao_detran;

import com.sistema.autolaudo.dto.integracao_detran.IntegracaoDetranResponse;
import com.sistema.autolaudo.model.integracao_detran.IntegracaoDetran;
import org.springframework.stereotype.Component;

@Component
public class IntegracaoDetranMapper {

    public IntegracaoDetranResponse toResponse(IntegracaoDetran integracao) {
        if (integracao == null) return null;
        IntegracaoDetranResponse resp = new IntegracaoDetranResponse();
        resp.setIdIntegracao(integracao.getIdIntegracao());
        resp.setApi(integracao.getApi());
        resp.setDataChamada(integracao.getDataChamada());
        resp.setResultado(integracao.getResultado());
        resp.setPlacaVeiculo(integracao.getPlacaVeiculo());
        return resp;
    }
}
