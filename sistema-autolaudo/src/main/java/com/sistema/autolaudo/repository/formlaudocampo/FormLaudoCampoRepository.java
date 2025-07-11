package com.sistema.autolaudo.repository.formlaudocampo;

import com.sistema.autolaudo.model.formlaudocampo.FormLaudoCampo;
import com.sistema.autolaudo.model.formlaudocampo.FormLaudoCampoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormLaudoCampoRepository extends JpaRepository<FormLaudoCampo, FormLaudoCampoId> {
    List<FormLaudoCampo> findByFormularioLaudo_IdFormulario(Long idFormulario);
}
