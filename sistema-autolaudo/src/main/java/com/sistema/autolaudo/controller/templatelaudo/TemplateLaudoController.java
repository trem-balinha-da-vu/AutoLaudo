package com.sistema.autolaudo.controller.templatelaudo;

import com.sistema.autolaudo.dto.templatelaudo.TemplateLaudoRequest;
import com.sistema.autolaudo.dto.templatelaudo.TemplateLaudoResponse;
import com.sistema.autolaudo.service.templatelaudo.TemplateLaudoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates-laudo")
@RequiredArgsConstructor
public class TemplateLaudoController {

    private final TemplateLaudoService templateLaudoService;

    @PostMapping
    public ResponseEntity<TemplateLaudoResponse> criar(@RequestBody TemplateLaudoRequest request) {
        return ResponseEntity.ok(templateLaudoService.criarTemplate(request));
    }

    @GetMapping("/{idTemplate}")
    public ResponseEntity<TemplateLaudoResponse> buscarPorId(@PathVariable Long idTemplate) {
        return ResponseEntity.ok(templateLaudoService.buscarPorId(idTemplate));
    }

    @GetMapping
    public ResponseEntity<List<TemplateLaudoResponse>> listar() {
        return ResponseEntity.ok(templateLaudoService.listarTodos());
    }

    @PutMapping("/{idTemplate}")
    public ResponseEntity<TemplateLaudoResponse> atualizar(@PathVariable Long idTemplate, @RequestBody TemplateLaudoRequest request) {
        return ResponseEntity.ok(templateLaudoService.atualizarTemplate(idTemplate, request));
    }

    @DeleteMapping("/{idTemplate}")
    public ResponseEntity<Void> deletar(@PathVariable Long idTemplate) {
        templateLaudoService.deletarTemplate(idTemplate);
        return ResponseEntity.noContent().build();
    }
}
