package com.sistema.autolaudo.mapper.norma;

import com.sistema.autolaudo.dto.norma.NormaRequest;
import com.sistema.autolaudo.dto.norma.NormaResponse;
import com.sistema.autolaudo.model.norma.Norma;
import org.springframework.stereotype.Component;

@Component
public class NormaMapper {

    public Norma toEntity(NormaRequest request) {
        if (request == null) return null;
        return Norma.builder()
                .nomeNorma(request.getNomeNorma())
                .tipo(request.getTipo())
                .textoNorma(request.getTextoNorma())
                .build();
    }

    public NormaResponse toResponse(Norma norma) {
        if (norma == null) return null;
        NormaResponse response = new NormaResponse();
        response.setIdNorma(norma.getIdNorma());
        response.setNomeNorma(norma.getNomeNorma());
        response.setTipo(norma.getTipo());
        response.setTextoNorma(norma.getTextoNorma());
        return response;
    }
}

