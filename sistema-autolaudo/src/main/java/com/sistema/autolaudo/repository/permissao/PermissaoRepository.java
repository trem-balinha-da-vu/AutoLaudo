package com.sistema.autolaudo.repository.permissao;

import com.sistema.autolaudo.model.permissao.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para entidade Permissao
 */
@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    Optional<Permissao> findByNomePermissao(String nomePermissao);
}
