package com.sistema.autolaudo.repository.orgaorequisitante;


import com.sistema.autolaudo.model.orgaorequisitante.OrgaoRequisitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgaoRequisitanteRepository extends JpaRepository<OrgaoRequisitante, Long> {
    Optional<OrgaoRequisitante> findByNome(String nome);
}
