package com.sistema.autolaudo.controller.laudostatus;

import com.sistema.autolaudo.dto.laudostatus.LaudoStatusResponse;
import com.sistema.autolaudo.service.laudostatus.LaudoStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laudo-status")
@RequiredArgsConstructor
public class LaudoStatusController {

    private final LaudoStatusService laudoStatusService;

    @GetMapping("/{idStatus}")
    public ResponseEntity<LaudoStatusResponse> buscarPorId(@PathVariable Long idStatus) {
        return ResponseEntity.ok(laudoStatusService.buscarPorId(idStatus));
    }

    @GetMapping
    public ResponseEntity<List<LaudoStatusResponse>> listar() {
        return ResponseEntity.ok(laudoStatusService.listarTodos());
    }

}

