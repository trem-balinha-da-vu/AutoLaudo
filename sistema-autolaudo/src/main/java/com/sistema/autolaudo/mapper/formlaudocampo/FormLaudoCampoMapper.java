package com.sistema.autolaudo.mapper.formlaudocampo;

import com.sistema.autolaudo.dto.formlaudocampo.FormLaudoCampoRequest;
import com.sistema.autolaudo.dto.formlaudocampo.FormLaudoCampoResponse;
import com.sistema.autolaudo.model.formlaudocampo.FormLaudoCampo;
import com.sistema.autolaudo.model.formlaudocampo.FormLaudoCampoId;
import com.sistema.autolaudo.model.formulariolaudo.FormularioLaudo;
import org.springframework.stereotype.Component;

@Component
public class FormLaudoCampoMapper {

    public FormLaudoCampo toEntity(FormLaudoCampoRequest request, FormularioLaudo formularioLaudo) {
        if (request == null) return null;
        FormLaudoCampoId id = new FormLaudoCampoId(request.getIdFormulario(), request.getNomeCampo());
        return FormLaudoCampo.builder()
                .id(id)
                .valor(request.getValor())
                .formularioLaudo(formularioLaudo)
                .build();
    }

    public FormLaudoCampoResponse toResponse(FormLaudoCampo campo) {
        if (campo == null) return null;
        FormLaudoCampoResponse resp = new FormLaudoCampoResponse();
        resp.setIdFormulario(campo.getId().getIdFormulario());
        resp.setNomeCampo(campo.getId().getNomeCampo());
        resp.setValor(campo.getValor());
        return resp;
    }
}
