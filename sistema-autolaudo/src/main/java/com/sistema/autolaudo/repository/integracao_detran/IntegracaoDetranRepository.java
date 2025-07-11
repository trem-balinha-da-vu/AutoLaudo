package com.sistema.autolaudo.repository.integracao_detran;

import com.sistema.autolaudo.model.integracao_detran.IntegracaoDetran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegracaoDetranRepository extends JpaRepository<IntegracaoDetran, Long> {

}
