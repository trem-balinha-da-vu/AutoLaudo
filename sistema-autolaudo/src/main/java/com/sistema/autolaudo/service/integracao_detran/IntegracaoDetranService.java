package com.sistema.autolaudo.service.integracao_detran;

import com.sistema.autolaudo.dto.integracao_detran.IntegracaoDetranResponse;

public interface IntegracaoDetranService {

    // Consulta dados do veículo via DETRAN e salva o log da integração
    IntegracaoDetranResponse consultarVeiculoPorChassi(String chassi);
}
