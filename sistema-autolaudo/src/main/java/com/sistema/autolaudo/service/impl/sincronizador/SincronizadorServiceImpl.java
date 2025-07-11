package com.sistema.autolaudo.service.impl.sincronizador;

import com.sistema.autolaudo.dto.sincronizador.SincronizadorRequest;
import com.sistema.autolaudo.model.historicoalteracao.HistoricoAlteracao;
import com.sistema.autolaudo.model.laudoveicular.LaudoVeicular;
import com.sistema.autolaudo.model.sincronizador.Sincronizador;
import com.sistema.autolaudo.model.usuario.Usuario;
import com.sistema.autolaudo.repository.historicoalteracao.HistoricoAlteracaoRepository;
import com.sistema.autolaudo.repository.laudoveicular.LaudoVeicularRepository;
import com.sistema.autolaudo.repository.sincronizador.SincronizadorRepository;
import com.sistema.autolaudo.repository.usuario.UsuarioRepository;
import com.sistema.autolaudo.service.sincronizador.SincronizadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SincronizadorServiceImpl implements SincronizadorService {

    private final SincronizadorRepository syncRepo;
    private final LaudoVeicularRepository laudoRepo;
    private final UsuarioRepository usuarioRepo;
    private final HistoricoAlteracaoRepository historicoRepo;

    @Override
    public void sincronizarAlteracoes(List<SincronizadorRequest> syncRequests) {
        for (SincronizadorRequest req : syncRequests) {
            LaudoVeicular laudo = laudoRepo.findById(req.getIdLaudo())
                    .orElseThrow(() -> new RuntimeException("Laudo não encontrado: " + req.getIdLaudo()));
            Usuario usuario = usuarioRepo.findById(req.getIdUsuario())
                    .orElse(null);

            // Atualiza campos, se houver alteração
            if (req.getDetalhamentoAnalises() != null)
                laudo.setDetalhamentoAnalises(req.getDetalhamentoAnalises());
            if (req.getConclusao() != null)
                laudo.setConclusao(req.getConclusao());
            if (req.getObservacoes() != null)
                laudo.setObservacoes(req.getObservacoes());

            laudoRepo.save(laudo);

            // Salva histórico da sincronização
            historicoRepo.save(HistoricoAlteracao.builder()
                    .dataAlteracao(req.getDataSync() != null ? req.getDataSync() : LocalDateTime.now())
                    .usuario(usuario)
                    .laudoVeicular(laudo)
                    .descricao("Alteração sincronizada do modo offline")
                    .build());

            // Registra sincronização
            syncRepo.save(Sincronizador.builder()
                    .dataSync(req.getDataSync() != null ? req.getDataSync() : LocalDateTime.now())
                    .usuario(usuario)
                    .laudoVeicular(laudo)
                    .build());
        }
    }
}

