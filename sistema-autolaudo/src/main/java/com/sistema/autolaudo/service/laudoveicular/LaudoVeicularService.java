package com.sistema.autolaudo.service.laudoveicular;

import com.sistema.autolaudo.dto.dashboard.MesCountDTO;
import com.sistema.autolaudo.dto.dashboard.StatusPizzaDTO;
import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularRequest;
import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularResponse;
import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularUpdateRequest;
import com.sistema.autolaudo.model.historicoalteracao.HistoricoAlteracao;

import java.util.List;

public interface LaudoVeicularService {
    LaudoVeicularResponse criarLaudo(LaudoVeicularRequest request);
    LaudoVeicularResponse atualizarLaudo(Long idLaudo, LaudoVeicularUpdateRequest request, Long idUsuarioAlteracao);
    void alterarStatus(Long idLaudo, String novoStatus, Long idUsuario);
    List<LaudoVeicularResponse> listarLaudosPorPerito(Long idPerito);
    List<StatusPizzaDTO> dashboardStatusPizza(Long idPerito); // Por perito
    List<StatusPizzaDTO> dashboardStatusPizzaEquipe();
    List<MesCountDTO> dashboardLaudosMes(Long idPerito, Long idStatus);
    List<HistoricoAlteracao> listarHistorico(Long idLaudo);
}
