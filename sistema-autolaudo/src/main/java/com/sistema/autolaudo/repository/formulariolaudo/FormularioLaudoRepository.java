package com.sistema.autolaudo.repository.formulariolaudo;

import com.sistema.autolaudo.model.formulariolaudo.FormularioLaudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioLaudoRepository extends JpaRepository<FormularioLaudo, Long> { }
