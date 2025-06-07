package com.sistema.autolaudo.service.gerenteregional;

import com.sistema.autolaudo.dto.gerenteregional.GerenteRegionalResponse;

import java.util.List;

public interface GerenteRegionalService {

    GerenteRegionalResponse getById(Long id);
    List<GerenteRegionalResponse> getAll();
}
