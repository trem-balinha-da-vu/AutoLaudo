package com.sistema.autolaudo.service.usuario;

import com.sistema.autolaudo.dto.usuario.UsuarioRequest;
import com.sistema.autolaudo.dto.usuario.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse criarUsuario(UsuarioRequest request);

    UsuarioResponse atualizarUsuario(Long id, UsuarioRequest request);

    void deletarUsuario(Long id);

    UsuarioResponse buscarPorId(Long id);

    List<UsuarioResponse> buscarTodos();
}
