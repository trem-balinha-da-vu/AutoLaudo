package com.sistema.autolaudo.repository.perfil;

import com.sistema.autolaudo.model.perfil.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para entidade Perfil
 */
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByPerfil(String perfil);
}
