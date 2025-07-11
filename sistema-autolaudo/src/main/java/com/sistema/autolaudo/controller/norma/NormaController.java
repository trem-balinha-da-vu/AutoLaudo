package com.sistema.autolaudo.controller.norma;

import com.sistema.autolaudo.dto.norma.NormaRequest;
import com.sistema.autolaudo.dto.norma.NormaResponse;
import com.sistema.autolaudo.service.norma.NormaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/normas")
@RequiredArgsConstructor
public class NormaController {

    private final NormaService normaService;

    @PostMapping
    public ResponseEntity<NormaResponse> criar(@RequestBody NormaRequest request) {
        return ResponseEntity.ok(normaService.criarNorma(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NormaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(normaService.buscarNormaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<NormaResponse>> listar() {
        return ResponseEntity.ok(normaService.listarNormas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NormaResponse> atualizar(@PathVariable Long id, @RequestBody NormaRequest request) {
        return ResponseEntity.ok(normaService.atualizarNorma(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        normaService.deletarNorma(id);
        return ResponseEntity.noContent().build();
    }
}