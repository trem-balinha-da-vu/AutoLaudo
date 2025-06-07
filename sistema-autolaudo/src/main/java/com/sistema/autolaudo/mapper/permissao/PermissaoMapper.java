package com.sistema.autolaudo.mapper.permissao;

import com.sistema.autolaudo.dto.permissao.PermissaoRequest;
import com.sistema.autolaudo.dto.permissao.PermissaoResponse;
import com.sistema.autolaudo.model.permissao.Permissao;
import org.springframework.stereotype.Component;

@Component
public class PermissaoMapper {

    public Permissao toModel(PermissaoRequest request) {
        Permissao model = new Permissao();
        model.setNomePermissao(request.getNomePermissao());
        return model;
    }

    public void updateModel(PermissaoRequest request, Permissao model) {
        if (request.getNomePermissao() != null) model.setNomePermissao(request.getNomePermissao());
    }

    public PermissaoResponse toResponse(Permissao model) {
        PermissaoResponse response = new PermissaoResponse();
        response.setId(model.getId());
        response.setNomePermissao(model.getNomePermissao());
        return response;
    }
}
