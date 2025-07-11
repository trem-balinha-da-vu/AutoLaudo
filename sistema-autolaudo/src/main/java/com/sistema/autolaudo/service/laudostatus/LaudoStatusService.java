package com.sistema.autolaudo.service.laudostatus;

import com.sistema.autolaudo.dto.laudostatus.LaudoStatusResponse;

import java.util.List;

public interface LaudoStatusService {
    LaudoStatusResponse buscarPorId(Long idStatus);
    List<LaudoStatusResponse> listarTodos();
}
