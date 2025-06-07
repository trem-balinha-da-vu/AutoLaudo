package com.sistema.autolaudo.service.permissao;

import com.sistema.autolaudo.dto.permissao.PermissaoRequest;
import com.sistema.autolaudo.dto.permissao.PermissaoResponse;

import java.util.List;

public interface PermissaoService {
    PermissaoResponse criarPermissao(PermissaoRequest request);
    PermissaoResponse atualizarPermissao(Long id, PermissaoRequest request);
    void deletarPermissao(Long id);
    PermissaoResponse buscarPorId(Long id);
    List<PermissaoResponse> buscarTodos();
}
