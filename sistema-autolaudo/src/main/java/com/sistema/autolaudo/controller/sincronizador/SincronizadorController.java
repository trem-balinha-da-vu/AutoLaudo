package com.sistema.autolaudo.controller.sincronizador;

import com.sistema.autolaudo.dto.sincronizador.SincronizadorRequest;
import com.sistema.autolaudo.service.sincronizador.SincronizadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sync")
@RequiredArgsConstructor
public class SincronizadorController {
    private SincronizadorService service;

    @PostMapping
    public ResponseEntity<Void> sincronizar(@RequestBody List<SincronizadorRequest> syncRequests) {
        service.sincronizarAlteracoes(syncRequests);
        return ResponseEntity.ok().build();
    }
}
