package com.sistema.autolaudo.controller.veiculo;

import com.sistema.autolaudo.dto.veiculo.VeiculoRequest;
import com.sistema.autolaudo.dto.veiculo.VeiculoResponse;
import com.sistema.autolaudo.service.veiculo.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<VeiculoResponse> criar(@RequestBody VeiculoRequest request) {
        return ResponseEntity.ok(veiculoService.criarVeiculo(request));
    }

    @GetMapping("/{placa}")
    public ResponseEntity<VeiculoResponse> buscarPorPlaca(@PathVariable String placa) {
        return ResponseEntity.ok(veiculoService.buscarPorPlaca(placa));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> listar() {
        return ResponseEntity.ok(veiculoService.listarTodos());
    }

    @PutMapping("/{placa}")
    public ResponseEntity<VeiculoResponse> atualizar(@PathVariable String placa, @RequestBody VeiculoRequest request) {
        return ResponseEntity.ok(veiculoService.atualizarVeiculo(placa, request));
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> deletar(@PathVariable String placa) {
        veiculoService.deletarVeiculo(placa);
        return ResponseEntity.noContent().build();
    }
}
