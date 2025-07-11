package com.sistema.autolaudo.controller.integracao_detran;

import com.sistema.autolaudo.dto.integracao_detran.IntegracaoDetranRequest;
import com.sistema.autolaudo.dto.integracao_detran.IntegracaoDetranResponse;
import com.sistema.autolaudo.service.integracao_detran.IntegracaoDetranService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/integracao-detran")
@RequiredArgsConstructor
public class IntegracaoDetranController {

    private final IntegracaoDetranService integracaoDetranService;

    @PostMapping("/consulta-veiculo")
    public ResponseEntity<IntegracaoDetranResponse> consultarPorChassi(
            @RequestBody IntegracaoDetranRequest request) {
        return ResponseEntity.ok(
                integracaoDetranService.consultarVeiculoPorChassi(request.getChassi())
        );
    }
}
