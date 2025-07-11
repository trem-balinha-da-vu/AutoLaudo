package com.sistema.autolaudo.repository.sincronizador;

import com.sistema.autolaudo.model.sincronizador.Sincronizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SincronizadorRepository extends JpaRepository<Sincronizador, Long> {}