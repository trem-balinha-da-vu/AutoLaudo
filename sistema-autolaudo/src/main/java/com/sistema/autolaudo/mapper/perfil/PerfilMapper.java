package com.sistema.autolaudo.mapper.perfil;

import com.sistema.autolaudo.dto.perfil.PerfilRequest;
import com.sistema.autolaudo.dto.perfil.PerfilResponse;
import com.sistema.autolaudo.model.perfil.Perfil;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {

    public Perfil toModel(PerfilRequest request) {
        Perfil model = new Perfil();
        model.setPerfil(request.getNomePerfil());
        return model;
    }

    public void updateModel(PerfilRequest request, Perfil model) {
        if (request.getNomePerfil() != null) model.setPerfil(request.getNomePerfil());
    }

    public PerfilResponse toResponse(Perfil model) {
        PerfilResponse response = new PerfilResponse();
        response.setId(model.getId_perfil());
        response.setNomePerfil(model.getPerfil());
        return response;
    }
}
