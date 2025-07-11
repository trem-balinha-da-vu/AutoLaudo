package com.sistema.autolaudo.service.sincronizador;

import com.sistema.autolaudo.dto.sincronizador.SincronizadorRequest;

import java.util.List;

public interface SincronizadorService {
    void sincronizarAlteracoes(List<SincronizadorRequest> syncRequests);
}


