package com.sistema.autolaudo.mapper.permissao;

import com.sistema.autolaudo.dto.permissao.PermissaoResponse;
import com.sistema.autolaudo.dto.permissao.UsuarioPermissaoRequest;
import com.sistema.autolaudo.dto.permissao.UsuarioPermissaoResponse;
import com.sistema.autolaudo.dto.usuario.UsuarioResponse;
import com.sistema.autolaudo.model.permissao.Permissao;
import com.sistema.autolaudo.model.permissao.UsuarioPermissao;
import com.sistema.autolaudo.model.usuario.Usuario;
import com.sistema.autolaudo.mapper.usuario.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPermissaoMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PermissaoMapper permissaoMapper;

    public UsuarioPermissao toModel(Usuario usuario, Permissao permissao) {
        UsuarioPermissao model = new UsuarioPermissao();
        model.setUsuario(usuario);
        model.setPermissao(permissao);
        // Se tiver ID composto, setar aqui também
        return model;
    }

    public UsuarioPermissaoResponse toResponse(UsuarioPermissao model) {
        UsuarioPermissaoResponse response = new UsuarioPermissaoResponse();
        UsuarioResponse usuarioResponse = usuarioMapper.toResponse(model.getUsuario());
        PermissaoResponse permissaoResponse = permissaoMapper.toResponse(model.getPermissao());
        response.setUsuario(usuarioResponse);
        response.setPermissao(permissaoResponse);
        return response;
    }
}
