package com.sistema.autolaudo.repository.templatelaudo;

import com.sistema.autolaudo.model.templatelaudo.TemplateLaudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateLaudoRepository extends JpaRepository<TemplateLaudo, Long> {
}
