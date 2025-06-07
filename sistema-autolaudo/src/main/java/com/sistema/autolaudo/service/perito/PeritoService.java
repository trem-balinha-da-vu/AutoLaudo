package com.sistema.autolaudo.service.perito;

import com.sistema.autolaudo.dto.gerenteregional.GerenteRegionalResponse;
import com.sistema.autolaudo.dto.perito.PeritoResponse;

import java.util.List;

public interface PeritoService {

    GerenteRegionalResponse promoverParaGerente(Long peritoId, String regional);

    PeritoResponse rebaixarParaPerito(Long peritoId);

    PeritoResponse getById(Long id);

    List<PeritoResponse> getAll();

}
