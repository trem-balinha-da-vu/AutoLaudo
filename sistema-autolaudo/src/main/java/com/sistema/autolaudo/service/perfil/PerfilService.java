package com.sistema.autolaudo.service.perfil;

import com.sistema.autolaudo.dto.perfil.PerfilRequest;
import com.sistema.autolaudo.dto.perfil.PerfilResponse;

import java.util.List;

public interface PerfilService {
    PerfilResponse criarPerfil(PerfilRequest request);
    PerfilResponse atualizarPerfil(Long id, PerfilRequest request);
    void deletarPerfil(Long id);
    PerfilResponse buscarPorId(Long id);
    List<PerfilResponse> buscarTodos();
}
