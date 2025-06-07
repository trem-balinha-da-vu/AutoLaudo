package com.sistema.autolaudo.mapper.gerenteregional;

import com.sistema.autolaudo.dto.gerenteregional.GerenteRegionalResponse;
import com.sistema.autolaudo.mapper.perito.PeritoMapper;
import com.sistema.autolaudo.model.gerenteregional.GerenteRegional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GerenteRegionalMapper {

    @Autowired
    private PeritoMapper peritoMapper;

    public GerenteRegionalResponse toResponse(GerenteRegional gerenteRegional) {
        GerenteRegionalResponse response = new GerenteRegionalResponse();
        response.setId(gerenteRegional.getId());
        response.setUuid(gerenteRegional.getUuid());
        response.setRegional(gerenteRegional.getRegional());
        response.setPerito(peritoMapper.toResponse(gerenteRegional.getPerito()));
        return response;
    }
}
