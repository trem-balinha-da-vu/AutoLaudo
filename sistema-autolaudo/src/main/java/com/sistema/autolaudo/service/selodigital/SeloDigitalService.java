package com.sistema.autolaudo.service.selodigital;


import com.sistema.autolaudo.dto.selodigital.SeloDigitalRequest;
import com.sistema.autolaudo.dto.selodigital.SeloDigitalResponse;

import java.util.List;

public interface SeloDigitalService {
    SeloDigitalResponse criarSelo(SeloDigitalRequest request);
    SeloDigitalResponse buscarPorId(Long idSelo);
    List<SeloDigitalResponse> listarTodos();
    SeloDigitalResponse atualizarSelo(Long idSelo, SeloDigitalRequest request);
    void deletarSelo(Long idSelo);
}

