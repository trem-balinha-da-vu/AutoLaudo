package com.sistema.autolaudo.mapper.selodigital;

import com.sistema.autolaudo.dto.selodigital.SeloDigitalRequest;
import com.sistema.autolaudo.dto.selodigital.SeloDigitalResponse;
import com.sistema.autolaudo.model.selodigital.SeloDigital;
import org.springframework.stereotype.Component;

@Component
public class SeloDigitalMapper {

    public SeloDigital toEntity(SeloDigitalRequest request) {
        if (request == null) return null;
        return SeloDigital.builder()
                .dataEmissao(request.getDataEmissao())
                .autoridadeEmissora(request.getAutoridadeEmissora())
                .hashConformidade(request.getHashConformidade())
                .build();
    }

    public SeloDigitalResponse toResponse(SeloDigital selo) {
        if (selo == null) return null;
        SeloDigitalResponse response = new SeloDigitalResponse();
        response.setIdSelo(selo.getIdSelo());
        response.setDataEmissao(selo.getDataEmissao());
        response.setAutoridadeEmissora(selo.getAutoridadeEmissora());
        response.setHashConformidade(selo.getHashConformidade());
        return response;
    }
}
