package com.sistema.autolaudo.controller.formlaudocampo;

import com.sistema.autolaudo.dto.formlaudocampo.FormLaudoCampoRequest;
import com.sistema.autolaudo.dto.formlaudocampo.FormLaudoCampoResponse;
import com.sistema.autolaudo.service.formlaudocampo.FormLaudoCampoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/form-laudo-campos")
@RequiredArgsConstructor
public class FormLaudoCampoController {

    private final FormLaudoCampoService service;

    @PostMapping
    public ResponseEntity<FormLaudoCampoResponse> criar(@RequestBody FormLaudoCampoRequest request) {
        return ResponseEntity.ok(service.criarCampo(request));
    }

    @GetMapping("/{idFormulario}/{nomeCampo}")
    public ResponseEntity<FormLaudoCampoResponse> buscarPorId(
            @PathVariable Long idFormulario, @PathVariable String nomeCampo) {
        return ResponseEntity.ok(service.buscarPorId(idFormulario, nomeCampo));
    }

    @GetMapping("/formulario/{idFormulario}")
    public ResponseEntity<List<FormLaudoCampoResponse>> listarPorFormulario(@PathVariable Long idFormulario) {
        return ResponseEntity.ok(service.listarPorFormulario(idFormulario));
    }

    @PutMapping
    public ResponseEntity<FormLaudoCampoResponse> atualizar(@RequestBody FormLaudoCampoRequest request) {
        return ResponseEntity.ok(service.atualizarCampo(request));
    }

    @DeleteMapping("/{idFormulario}/{nomeCampo}")
    public ResponseEntity<Void> deletar(@PathVariable Long idFormulario, @PathVariable String nomeCampo) {
        service.deletarCampo(idFormulario, nomeCampo);
        return ResponseEntity.noContent().build();
    }
}
