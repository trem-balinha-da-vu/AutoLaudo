package com.sistema.autolaudo.repository.laudoveicular;

import com.sistema.autolaudo.dto.dashboard.MesCountDTO;
import com.sistema.autolaudo.dto.dashboard.StatusPizzaDTO;
import com.sistema.autolaudo.model.laudoveicular.LaudoVeicular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaudoVeicularRepository extends JpaRepository<LaudoVeicular, Long> {
    List<LaudoVeicular> findByPerito_Id(Long idPerito);

    // Dashboard: pizza por status
    @Query("SELECT s.nome AS status, COUNT(l) AS quantidade " +
            "FROM LaudoVeicular l " +
            "JOIN l.status s " +
            "WHERE l.perito.id = :idPerito " +
            "GROUP BY s.nome")
    List<StatusPizzaDTO> countLaudosByStatusPerito(@Param("idPerito") Long idPerito);

    // Dashboard: pizza geral (equipe)
    @Query("SELECT s.nome AS status, COUNT(l) AS quantidade " +
            "FROM LaudoVeicular l " +
            "JOIN l.status s " +
            "GROUP BY s.nome")
    List<StatusPizzaDTO> countLaudosByStatusEquipe();

    // Dashboard: por mês, podendo filtrar por perito e status
    @Query("SELECT FUNCTION('DATE_TRUNC', 'month', l.dataCriacao) AS mes, COUNT(l) AS quantidade " +
            "FROM LaudoVeicular l " +
            "WHERE (:idPerito IS NULL OR l.perito.id = :idPerito) " +
            "AND (:idStatus IS NULL OR l.status.idStatus = :idStatus) " +
            "GROUP BY mes " +
            "ORDER BY mes")
    List<MesCountDTO> countLaudosPorMes(
            @Param("idPerito") Long idPerito,
            @Param("idStatus") Long idStatus
    );

    Optional<LaudoVeicular> findTopByPerito_IdOrderByDataCriacaoDesc(Long idPerito);
}
