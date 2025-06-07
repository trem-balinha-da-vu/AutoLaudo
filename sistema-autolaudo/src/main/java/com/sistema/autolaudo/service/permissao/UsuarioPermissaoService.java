package com.sistema.autolaudo.service.permissao;

import com.sistema.autolaudo.dto.permissao.UsuarioPermissaoRequest;
import com.sistema.autolaudo.dto.permissao.UsuarioPermissaoResponse;

import java.util.List;

public interface UsuarioPermissaoService {
    UsuarioPermissaoResponse criar(Long usuarioId, Long permissaoId);
    void deletar(Long usuarioId, Long permissaoId);
    List<UsuarioPermissaoResponse> listarPorUsuario(Long usuarioId);
}
