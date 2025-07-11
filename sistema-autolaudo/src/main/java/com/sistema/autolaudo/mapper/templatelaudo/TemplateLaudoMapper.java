package com.sistema.autolaudo.mapper.templatelaudo;

import com.sistema.autolaudo.dto.templatelaudo.TemplateLaudoRequest;
import com.sistema.autolaudo.dto.templatelaudo.TemplateLaudoResponse;
import com.sistema.autolaudo.model.templatelaudo.TemplateLaudo;
import org.springframework.stereotype.Component;

@Component
public class TemplateLaudoMapper {

    public TemplateLaudo toEntity(TemplateLaudoRequest request) {
        if (request == null) return null;
        return TemplateLaudo.builder()
                .nome(request.getNome())
                .versao(request.getVersao())
                .build();
    }

    public TemplateLaudoResponse toResponse(TemplateLaudo template) {
        if (template == null) return null;
        TemplateLaudoResponse response = new TemplateLaudoResponse();
        response.setIdTemplate(template.getIdTemplate());
        response.setNome(template.getNome());
        response.setVersao(template.getVersao());
        return response;
    }
}
