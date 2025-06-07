package com.sistema.autolaudo.mapper.usuario;

import com.sistema.autolaudo.model.usuario.Usuario;
import com.sistema.autolaudo.dto.usuario.UsuarioResponse;
import com.sistema.autolaudo.dto.usuario.UsuarioRequest;
import com.sistema.autolaudo.model.perfil.Perfil;
import com.sistema.autolaudo.model.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toModel(UsuarioRequest request, Perfil perfil) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenhaHash(request.getSenha());
        usuario.setPerfil(perfil);
        return usuario;
    }

    public void updateModel(UsuarioRequest request, Usuario usuario, Perfil perfil) {
        if (request.getNome() != null) usuario.setNome(request.getNome());
        if (request.getEmail() != null) usuario.setEmail(request.getEmail());
        if (request.getSenha() != null) usuario.setSenhaHash(request.getSenha());
        if (perfil != null) usuario.setPerfil(perfil);
    }

    public UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setUuid(usuario.getUuid());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setPerfilNome(usuario.getPerfil() != null ? usuario.getPerfil().getPerfil(): null);
        return response;
    }
}
