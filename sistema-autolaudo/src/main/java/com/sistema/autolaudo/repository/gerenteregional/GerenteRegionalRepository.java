package com.sistema.autolaudo.repository.gerenteregional;

import com.sistema.autolaudo.model.gerenteregional.GerenteRegional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GerenteRegionalRepository extends JpaRepository<GerenteRegional, Long> {

    Optional<GerenteRegional> findByPeritoId(Long peritoId);

    void deleteByPeritoId(Long peritoId);

}

