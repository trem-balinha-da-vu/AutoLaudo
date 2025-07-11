package com.sistema.autolaudo.controller.formulariolaudo;

import com.sistema.autolaudo.dto.formulariolaudo.FormularioLaudoRequest;
import com.sistema.autolaudo.dto.formulariolaudo.FormularioLaudoResponse;
import com.sistema.autolaudo.model.formulariolaudo.FormularioLaudo;
import com.sistema.autolaudo.service.formulariolaudo.FormularioLaudoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formularios")
@RequiredArgsConstructor
public class FormularioLaudoController {

    private FormularioLaudoService formularioService;

    // Cria formulário para um laudo, a partir de template
    @PostMapping
    public ResponseEntity<FormularioLaudo> criar(@RequestBody FormularioLaudoRequest req) {
        return ResponseEntity.ok(formularioService.criarFormulario(req));
    }

    // Atualiza um campo específico no formulário
    @PatchMapping("/{idFormulario}/campo")
    public ResponseEntity<FormularioLaudo> preencherCampo(
            @PathVariable Long idFormulario,
            @RequestParam String nomeCampo,
            @RequestParam String valor
    ) {
        return ResponseEntity.ok(formularioService.preencherCampo(idFormulario, nomeCampo, valor));
    }

    // Busca formulário por id
    @GetMapping("/{idFormulario}")
    public ResponseEntity<FormularioLaudo> buscarPorId(@PathVariable Long idFormulario) {
        return ResponseEntity.ok(formularioService.buscarPorId(idFormulario));
    }

    // Busca o último formulário do perito para auto-complete
    @GetMapping("/perito/{idPerito}/ultimo")
    public ResponseEntity<FormularioLaudo> buscarUltimoPorPerito(@PathVariable Long idPerito) {
        return ResponseEntity.ok(formularioService.buscarUltimoPorPerito(idPerito));
    }

    // Lista todos formulários de um perito
    @GetMapping("/perito/{idPerito}")
    public ResponseEntity<List<FormularioLaudo>> listarPorPerito(@PathVariable Long idPerito) {
        return ResponseEntity.ok(formularioService.listarPorPerito(idPerito));
    }
}
