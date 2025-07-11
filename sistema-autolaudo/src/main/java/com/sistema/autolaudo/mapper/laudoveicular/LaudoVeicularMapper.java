package com.sistema.autolaudo.mapper.laudoveicular;


import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularRequest;
import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularResponse;
import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularUpdateRequest;
import com.sistema.autolaudo.model.formulariolaudo.FormularioLaudo;
import com.sistema.autolaudo.model.laudostatus.LaudoStatus;
import com.sistema.autolaudo.model.laudoveicular.LaudoVeicular;
import com.sistema.autolaudo.model.norma.Norma;
import com.sistema.autolaudo.model.orgaorequisitante.OrgaoRequisitante;
import com.sistema.autolaudo.model.usuario.Usuario;
import com.sistema.autolaudo.model.veiculo.Veiculo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LaudoVeicularMapper {

    public LaudoVeicular toEntity(
            LaudoVeicularRequest request,
            LaudoStatus status,
            OrgaoRequisitante orgao,
            Veiculo veiculo,
            Usuario perito,
            List<Norma> normas,
            FormularioLaudo formulario
    ) {
        return LaudoVeicular.builder()
                .dataCriacao(java.time.LocalDateTime.now())
                .dataEntrega(request.getDataEntrega())
                .resumoProblema(request.getResumoProblema())
                .status(status)
                .orgaoRequisitante(orgao)
                .veiculo(veiculo)
                .perito(perito)
                .normasAplicadas(normas)
                .formularioLaudo(formulario)
                .build();
    }

    public void updateEntity(LaudoVeicular laudo, LaudoVeicularUpdateRequest req, LaudoStatus novoStatus) {
        if (req.getDetalhamentoAnalises() != null)
            laudo.setDetalhamentoAnalises(req.getDetalhamentoAnalises());
        if (req.getConclusao() != null)
            laudo.setConclusao(req.getConclusao());
        if (req.getObservacoes() != null)
            laudo.setObservacoes(req.getObservacoes());
        if (novoStatus != null)
            laudo.setStatus(novoStatus);
    }

    public LaudoVeicularResponse toResponse(LaudoVeicular laudo) {
        LaudoVeicularResponse resp = new LaudoVeicularResponse();
        resp.setIdLaudo(laudo.getIdLaudo());
        resp.setDataCriacao(laudo.getDataCriacao());
        resp.setDataEntrega(laudo.getDataEntrega());
        resp.setDataEmissao(laudo.getDataEmissao());
        resp.setStatus(laudo.getStatus() != null ? laudo.getStatus().getNome() : null);
        resp.setOrgaoRequisitante(laudo.getOrgaoRequisitante() != null ? laudo.getOrgaoRequisitante().getNome() : null);
        resp.setResumoProblema(laudo.getResumoProblema());
        resp.setDetalhamentoAnalises(laudo.getDetalhamentoAnalises());
        resp.setConclusao(laudo.getConclusao());
        resp.setObservacoes(laudo.getObservacoes());
        resp.setIdPerito(laudo.getPerito() != null ? laudo.getPerito().getId() : null);
        resp.setPlacaVeiculo(laudo.getVeiculo() != null ? laudo.getVeiculo().getPlaca() : null);
        resp.setNormas(laudo.getNormasAplicadas() != null ?
                laudo.getNormasAplicadas().stream().map(Norma::getNomeNorma).collect(Collectors.toList()) : null);
        return resp;
    }
}
