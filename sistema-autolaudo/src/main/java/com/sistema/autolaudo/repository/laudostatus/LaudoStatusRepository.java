package com.sistema.autolaudo.repository.laudostatus;

import com.sistema.autolaudo.model.laudostatus.LaudoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaudoStatusRepository extends JpaRepository<LaudoStatus, Long> {
    Optional<LaudoStatus> findByNome(String nome);
}
