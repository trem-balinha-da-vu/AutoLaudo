package com.sistema.autolaudo.repository.historicoalteracao;

import com.sistema.autolaudo.model.historicoalteracao.HistoricoAlteracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoAlteracaoRepository extends JpaRepository<HistoricoAlteracao, Long> {
    List<HistoricoAlteracao> findByLaudoVeicular_IdLaudo(Long idLaudo);
}