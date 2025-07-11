package com.sistema.autolaudo.service.impl.laudoveicular;

import com.sistema.autolaudo.dto.dashboard.MesCountDTO;
import com.sistema.autolaudo.dto.dashboard.StatusPizzaDTO;
import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularRequest;
import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularResponse;
import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularUpdateRequest;
import com.sistema.autolaudo.mapper.laudoveicular.LaudoVeicularMapper;
import com.sistema.autolaudo.model.formulariolaudo.FormularioLaudo;
import com.sistema.autolaudo.model.historicoalteracao.HistoricoAlteracao;
import com.sistema.autolaudo.model.laudostatus.LaudoStatus;
import com.sistema.autolaudo.model.laudoveicular.LaudoVeicular;
import com.sistema.autolaudo.model.norma.Norma;
import com.sistema.autolaudo.model.orgaorequisitante.OrgaoRequisitante;
import com.sistema.autolaudo.model.usuario.Usuario;
import com.sistema.autolaudo.model.veiculo.Veiculo;
import com.sistema.autolaudo.repository.formulariolaudo.FormularioLaudoRepository;
import com.sistema.autolaudo.repository.historicoalteracao.HistoricoAlteracaoRepository;
import com.sistema.autolaudo.repository.laudostatus.LaudoStatusRepository;
import com.sistema.autolaudo.repository.laudoveicular.LaudoVeicularRepository;
import com.sistema.autolaudo.repository.norma.NormaRepository;
import com.sistema.autolaudo.repository.orgaorequisitante.OrgaoRequisitanteRepository;
import com.sistema.autolaudo.repository.usuario.UsuarioRepository;
import com.sistema.autolaudo.repository.veiculo.VeiculoRepository;
import com.sistema.autolaudo.service.laudoveicular.LaudoVeicularService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaudoVeicularServiceImpl implements LaudoVeicularService {

    private final LaudoVeicularRepository laudoRepo;
    private final LaudoStatusRepository statusRepo;
    private final OrgaoRequisitanteRepository orgaoRepo;
    private final UsuarioRepository usuarioRepo;
    private final VeiculoRepository veiculoRepo;
    private final NormaRepository normaRepo;
    private final FormularioLaudoRepository formularioRepo;
    private final HistoricoAlteracaoRepository historicoRepo;
    private final LaudoVeicularMapper mapper;

    @Override
    public LaudoVeicularResponse criarLaudo(LaudoVeicularRequest request) {
        LaudoStatus status = statusRepo.findByNome("PENDENTE")
                .orElseThrow(() -> new RuntimeException("Status PENDENTE não encontrado"));
        OrgaoRequisitante orgao = orgaoRepo.findById(request.getIdOrgao())
                .orElseThrow(() -> new RuntimeException("Órgão não encontrado"));
        Veiculo veiculo = veiculoRepo.findById(request.getPlacaVeiculo())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        Usuario perito = usuarioRepo.findById(request.getIdPerito())
                .orElseThrow(() -> new RuntimeException("Perito não encontrado"));

        List<Norma> normas = request.getIdNormas() == null ? List.of() :
                normaRepo.findAllById(request.getIdNormas());

        FormularioLaudo formulario = null;
        if(request.getIdFormulario() != null) {
            formulario = formularioRepo.findById(request.getIdFormulario())
                    .orElseThrow(() -> new RuntimeException("Formulário não encontrado"));
        }

        LaudoVeicular laudo = mapper.toEntity(request, status, orgao, veiculo, perito, normas, formulario);
        laudo.setDataCriacao(LocalDateTime.now());

        LaudoVeicular salvo = laudoRepo.save(laudo);
        historicoRepo.save(HistoricoAlteracao.builder()
                .dataAlteracao(LocalDateTime.now())
                .usuario(perito)
                .laudoVeicular(salvo)
                .descricao("Laudo criado - status: PENDENTE")
                .build()
        );
        return mapper.toResponse(salvo);
    }

    @Override
    public LaudoVeicularResponse atualizarLaudo(Long idLaudo, LaudoVeicularUpdateRequest req, Long idUsuario) {
        LaudoVeicular laudo = laudoRepo.findById(idLaudo)
                .orElseThrow(() -> new RuntimeException("Laudo não encontrado"));

        LaudoStatus novoStatus = null;
        if(req.getIdStatus() != null)
            novoStatus = statusRepo.findById(req.getIdStatus())
                    .orElseThrow(() -> new RuntimeException("Status não encontrado"));

        mapper.updateEntity(laudo, req, novoStatus);

        LaudoVeicular salvo = laudoRepo.save(laudo);
        historicoRepo.save(HistoricoAlteracao.builder()
                .dataAlteracao(LocalDateTime.now())
                .usuario(usuarioRepo.findById(idUsuario).orElse(null))
                .laudoVeicular(salvo)
                .descricao("Alteração de laudo (detalhamento, conclusão, status...)")
                .build()
        );
        return mapper.toResponse(salvo);
    }

    @Override
    public void alterarStatus(Long idLaudo, String novoStatus, Long idUsuario) {
        LaudoVeicular laudo = laudoRepo.findById(idLaudo)
                .orElseThrow(() -> new RuntimeException("Laudo não encontrado"));

        LaudoStatus status = statusRepo.findByNome(novoStatus)
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));

        laudo.setStatus(status);
        laudoRepo.save(laudo);

        historicoRepo.save(HistoricoAlteracao.builder()
                .dataAlteracao(LocalDateTime.now())
                .usuario(usuarioRepo.findById(idUsuario).orElse(null))
                .laudoVeicular(laudo)
                .descricao("Status alterado para " + novoStatus)
                .build()
        );
    }

    @Override
    public List<LaudoVeicularResponse> listarLaudosPorPerito(Long idPerito) {
        return laudoRepo.findByPerito_Id(idPerito).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatusPizzaDTO> dashboardStatusPizza(Long idPerito) {
        return laudoRepo.countLaudosByStatusPerito(idPerito);
    }

    @Override
    public List<StatusPizzaDTO> dashboardStatusPizzaEquipe() {
        return laudoRepo.countLaudosByStatusEquipe();
    }

    @Override
    public List<MesCountDTO> dashboardLaudosMes(Long idPerito, Long idStatus) {
        return laudoRepo.countLaudosPorMes(idPerito, idStatus);
    }

    @Override
    public List<HistoricoAlteracao> listarHistorico(Long idLaudo) {
        return historicoRepo.findByLaudoVeicular_IdLaudo(idLaudo);
    }
}