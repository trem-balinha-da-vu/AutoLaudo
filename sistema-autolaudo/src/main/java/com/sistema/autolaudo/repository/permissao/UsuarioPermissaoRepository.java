package com.sistema.autolaudo.repository.permissao;

import com.sistema.autolaudo.model.permissao.UsuarioPermissao;
import com.sistema.autolaudo.model.permissao.UsuarioPermissaoId;
import com.sistema.autolaudo.model.permissao.UsuarioPermissaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para entidade UsuarioPermissao (chave composta)
 */
@Repository
public interface UsuarioPermissaoRepository extends JpaRepository<UsuarioPermissao, UsuarioPermissaoId> {

    List<UsuarioPermissao> findAllByUsuarioId(Long usuarioId);

    List<UsuarioPermissao> findAllByPermissaoId(Long permissaoId);

    List<UsuarioPermissao> findByUsuarioId(Long usuarioId);

    void deleteById(UsuarioPermissaoId id);
}
