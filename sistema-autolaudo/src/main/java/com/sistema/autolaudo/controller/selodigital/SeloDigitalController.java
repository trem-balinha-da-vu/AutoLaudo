package com.sistema.autolaudo.controller.selodigital;

import com.sistema.autolaudo.dto.selodigital.SeloDigitalRequest;
import com.sistema.autolaudo.dto.selodigital.SeloDigitalResponse;
import com.sistema.autolaudo.service.selodigital.SeloDigitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/selos-digitais")
@RequiredArgsConstructor
public class SeloDigitalController {

    private final SeloDigitalService seloDigitalService;

    @PostMapping
    public ResponseEntity<SeloDigitalResponse> criar(@RequestBody SeloDigitalRequest request) {
        return ResponseEntity.ok(seloDigitalService.criarSelo(request));
    }

    @GetMapping("/{idSelo}")
    public ResponseEntity<SeloDigitalResponse> buscarPorId(@PathVariable Long idSelo) {
        return ResponseEntity.ok(seloDigitalService.buscarPorId(idSelo));
    }

    @GetMapping
    public ResponseEntity<List<SeloDigitalResponse>> listar() {
        return ResponseEntity.ok(seloDigitalService.listarTodos());
    }

    @PutMapping("/{idSelo}")
    public ResponseEntity<SeloDigitalResponse> atualizar(@PathVariable Long idSelo, @RequestBody SeloDigitalRequest request) {
        return ResponseEntity.ok(seloDigitalService.atualizarSelo(idSelo, request));
    }

    @DeleteMapping("/{idSelo}")
    public ResponseEntity<Void> deletar(@PathVariable Long idSelo) {
        seloDigitalService.deletarSelo(idSelo);
        return ResponseEntity.noContent().build();
    }
}
