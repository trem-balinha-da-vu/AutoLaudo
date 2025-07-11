package com.sistema.autolaudo.controller.laudoveicular;

import com.sistema.autolaudo.dto.dashboard.MesCountDTO;
import com.sistema.autolaudo.dto.dashboard.StatusPizzaDTO;
import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularRequest;
import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularResponse;
import com.sistema.autolaudo.dto.laudoveicular.LaudoVeicularUpdateRequest;
import com.sistema.autolaudo.model.historicoalteracao.HistoricoAlteracao;
import com.sistema.autolaudo.service.laudoveicular.LaudoVeicularService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laudos")
@RequiredArgsConstructor
public class LaudoVeicularController {

    private final LaudoVeicularService laudoService;

    @PostMapping
    public ResponseEntity<LaudoVeicularResponse> criar(@RequestBody LaudoVeicularRequest req) {
        return ResponseEntity.ok(laudoService.criarLaudo(req));
    }

    @PutMapping("/{idLaudo}")
    public ResponseEntity<LaudoVeicularResponse> atualizar(
            @PathVariable Long idLaudo,
            @RequestBody LaudoVeicularUpdateRequest req,
            @RequestParam Long idUsuario
    ) {
        return ResponseEntity.ok(laudoService.atualizarLaudo(idLaudo, req, idUsuario));
    }

    @PatchMapping("/{idLaudo}/status")
    public ResponseEntity<Void> alterarStatus(
            @PathVariable Long idLaudo,
            @RequestParam String novoStatus,
            @RequestParam Long idUsuario
    ) {
        laudoService.alterarStatus(idLaudo, novoStatus, idUsuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/perito/{idPerito}")
    public ResponseEntity<List<LaudoVeicularResponse>> listarPorPerito(@PathVariable Long idPerito) {
        return ResponseEntity.ok(laudoService.listarLaudosPorPerito(idPerito));
    }

    // Dashboard (pizza por status)
    @GetMapping("/dashboard/status/{idPerito}")
    public ResponseEntity<List<StatusPizzaDTO>> dashboardStatusPizza(@PathVariable Long idPerito) {
        return ResponseEntity.ok(laudoService.dashboardStatusPizza(idPerito));
    }

    @GetMapping("/dashboard/status")
    public ResponseEntity<List<StatusPizzaDTO>> dashboardStatusEquipe() {
        return ResponseEntity.ok(laudoService.dashboardStatusPizzaEquipe());
    }

    // Dashboard (acumulado por mês)
    @GetMapping("/dashboard/mes")
    public ResponseEntity<List<MesCountDTO>> dashboardLaudosMes(
            @RequestParam(required = false) Long idPerito,
            @RequestParam(required = false) Long idStatus
    ) {
        return ResponseEntity.ok(laudoService.dashboardLaudosMes(idPerito, idStatus));
    }

    // Histórico do laudo
    @GetMapping("/{idLaudo}/historico")
    public ResponseEntity<List<HistoricoAlteracao>> historico(@PathVariable Long idLaudo) {
        return ResponseEntity.ok(laudoService.listarHistorico(idLaudo));
    }
}
